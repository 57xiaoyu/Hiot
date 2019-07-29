package com.cch.hiot2.ui.main.mine;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BasePresenter;
import com.cch.hiot2.entity.LoginEntity;
import com.cch.hiot2.entity.UserEntity;
import com.cch.hiot2.http.HttpResult;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.ProgressDialogSubscriber;
import com.cch.hiot2.http.UserPreferencesHelper;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MinePresenter extends BasePresenter<MineView> {
    @Inject
    HttpService service;
    @Inject
    UserPreferencesHelper preferencesHelper;
    @Inject
    Activity activity;

    @Inject
    public MinePresenter() {
    }

    public void getUserInfo() {
        Observable<HttpResult<UserEntity>> observable = service.getUserInfo(preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult<UserEntity>>(activity) {
            @Override
            public void onNext(HttpResult<UserEntity> result) {
                if (result != null) {
                    if (result.getStatus() == 1 && result.getData() != null) {
                        //更新UI
                        getView().showUserHead(HttpService.IMAGE_BASE_URL + result.getData().getImg());
                        getView().showUserName(result.getData().getUsername());
                        getView().showUserEmail(result.getData().getEmail());
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("获取用户信息失败result==null");
                }
            }
        });
    }

    public void logout() {
        Observable<HttpResult> observable = service.logout(preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult>(activity) {
            @Override
            public void onNext(HttpResult result) {
                if (result != null) {
                    if (result.getStatus() == 1) {
                        getView().showToast(result.getMsg());
                        //退出登录成功
                        getView().logoutSucceed();
                        //清空保存的用户信息
                        preferencesHelper.clear();
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("退出登录失败result==null");
                }
            }
        });
    }

    //点击头像执行
    public void changeHead() {
        //1、检查权限
        requestPermission();
    }

    //检查权限
    private void requestPermission() {
        Acp.getInstance(activity).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.CAMERA
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        //选择 相册还是拍照
                        choosePicDialog();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        getView().showToast(permissions.toString() + "权限拒绝");
                    }
                });
    }

    //2、弹出选择图片的Dialog
    private void choosePicDialog() {
        NormalSelectionDialog chooseDialog = new NormalSelectionDialog.Builder(activity)
                .setlTitleVisible(false)   //设置是否显示标题
                .setTitleHeight(65)   //设置标题高度
                .setTitleText("请选择")  //设置标题提示文本
                .setTitleTextSize(16) //设置标题字体大小 sp
                .setTitleTextColor(android.R.color.black) //设置标题文本颜色
                .setItemHeight(60)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(android.R.color.black)  //设置item字体颜色
                .setItemTextSize(16)  //设置item字体大小
                .setCancleButtonText("取消")  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                        switch (position) {
                            case 0: // 选择本地照片
                                choseHeadImageFromGallery();
                                dialog.dismiss();
                                break;
                            case 1:// 拍照
                                choseHeadImageFromCameraCapture();
                                dialog.dismiss();
                                break;
                        }
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build();
        ArrayList<String> s = new ArrayList<>();
        s.add("从相册里选择照片");
        s.add("拍照");
        chooseDialog.setDatas(s);
        chooseDialog.show();
    }


    public static final int TAKE_PICTURE = 55;//拍照
    private static final int CROP_SMALL_PICTURE = 44;//裁剪

    //private Bitmap mBitmap;


    public static final int CHOOSE_PICTURE = 66;//从本地选择

    /**
     * 选择本地照片
     */
    public void choseHeadImageFromGallery() {
        Intent openAlbumIntent = new Intent();
        openAlbumIntent.setAction(Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
        getView().getFragment().startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
    }

    private Uri tempUri;//拍照保存的

    /**
     * 启用本地相机
     */
    private void choseHeadImageFromCameraCapture() {
        Intent openCameraIntent = new Intent();
        openCameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // 将拍照所得的相片保存到SD卡根目录
        File photoOutputFile;
        if (hasSdcard()) {
            //URI:file:///storage/emulated/0/head.jpg
            photoOutputFile = new File(Environment.getExternalStorageDirectory(), "head.jpg");
        } else {
            //URI:file:///data/user/0/com.cch.hiot2/files/head.jpg
            photoOutputFile = new File(activity.getFilesDir(), "head.jpg");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tempUri = FileProvider.getUriForFile(
                    activity, "com.cch.hiot2.fileprovider", photoOutputFile);
        } else {
            tempUri = Uri.fromFile(photoOutputFile);
        }
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        getView().getFragment().startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    //裁剪图片方法实现
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("dyp", "The uri is not exist.");
        }
        tempUri = uri;
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //需要加上这两句话 ： uri 权限
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        getView().getFragment().startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    private String savePhoto(Bitmap photoBitmap, String path, String photoName) {
        String localPath = null;
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File photoFile = new File(path, photoName + ".jpg");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                            fileOutputStream)) { //
                        localPath = photoFile.getPath();
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        fileOutputStream = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return localPath;
    }

    //上传图片
    private void uploadPic(File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        Observable<HttpResult> observable = service.uploadFile(multipartFile, preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult>(activity) {
            @Override
            public void onNext(HttpResult result) {
                if (result != null) {
                    if (result.getStatus() == 1) {
                        //成功
                        getView().showToast(result.getMsg());
                        //重新刷新数据
                        getUserInfo();
                    }
                    if (result.getStatus() == -100) {
                        //token超时
                        getView().showToast(result.getMsg());
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("上传失败result==null");
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE://拍照
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE://本地照片选择
                    if (data != null) {
                        cutImage(data.getData()); // 对图片进行裁剪处理
                    }
                    break;
                case CROP_SMALL_PICTURE://
                    if (data != null && data.getExtras() != null) {
                        //获取返回的图片数据
                        Bitmap bitmap = data.getExtras().getParcelable("data");
                        //调用savePhoto保存图片
                        String fileName = String.valueOf(System.currentTimeMillis());
                        String filePath;
                        if (hasSdcard()) {
                            filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                        } else {
                            filePath = activity.getFilesDir().getAbsolutePath();
                        }
                        //保存图片
                        String imagePath = savePhoto(bitmap, filePath, fileName);
                        File file = new File(imagePath);
                        //上传图片
                        uploadPic(file);
                    }
                    break;
            }
        }
    }
}

