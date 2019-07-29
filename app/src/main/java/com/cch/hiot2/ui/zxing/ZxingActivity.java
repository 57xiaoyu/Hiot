package com.cch.hiot2.ui.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.utils.ImageUtil;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZxingActivity extends BaseActivity<ZxingView, ZxingPresenter>
        implements ZxingView, CodeUtils.AnalyzeCallback {

    private static final int REQUEST_IMAGE = 1001;
    @Inject
    ZxingPresenter zxingPresenter;

    @BindView(R.id.fl_my_container)
    FrameLayout fl_my_container;
    @BindView(R.id.flash_lamp)
    TextView flash_lamp;
    @BindView(R.id.album)
    TextView album;
    private CaptureFragment captureFragment;

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected ZxingPresenter createPresenter() {
        return zxingPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        ButterKnife.bind(this);
        //初始化
        initView();
    }

    @OnClick(R.id.album)
    void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public boolean isOpen = false;

    @OnClick(R.id.flash_lamp)
    void openFlash() {
        if (!isOpen) {
            CodeUtils.isLightEnable(true);
            isOpen = true;
        } else {
            CodeUtils.isLightEnable(false);
            isOpen = false;
        }
    }

    private void initView() {
        // 执行扫描Fragment的初始化操作
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.fragment_zxing);
        //扫描结果回调接口
        captureFragment.setAnalyzeCallback(this);
        //替换我们的扫描控件
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_my_container, captureFragment)
                .commit();
    }

    //选择图片的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    //选择系统图片并解析
                    CodeUtils.analyzeBitmap(
                            ImageUtil.getImageAbsolutePath(this, uri),
                            this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭闪光灯
        CodeUtils.isLightEnable(false);
    }

    //二维码解析回调 解析成功
    @Override
    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
        //TODO:发送网络请求
        //网络请求 添加设备
        zxingPresenter.addEquipment(result);
    }

    //二维码解析回调  解析失败
    @Override
    public void onAnalyzeFailed() {
        showToast("解析失败");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ZxingActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addSucceed() {
        //添加成功，关闭自己，回到设备列表页
        finish();
    }
}
