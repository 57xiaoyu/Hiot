package com.cch.hiot2.ui.main.equipment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.cch.hiot2.base.BasePresenter;
import com.cch.hiot2.entity.HolderDeviceEntity;
import com.cch.hiot2.http.HttpResult;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.ProgressDialogSubscriber;
import com.cch.hiot2.http.UserPreferencesHelper;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class EquipmentPresenter extends BasePresenter <EquipmentView>{

    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public EquipmentPresenter() {
    }

    public void getDeviceList() {
        Observable<HttpResult<List<HolderDeviceEntity>>> observable = service.getDeviceList(1, preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult<List<HolderDeviceEntity>>>(activity) {
            @Override
            public void onNext(HttpResult<List<HolderDeviceEntity>> result) {
                getView().stopRefresh();
                if (result != null) {
                    if (result.getStatus() == 1) {
                        getView().showToast(result.getMsg());
                        //获取绑定的设备列表成功
                        getView().refreshData(result.getData());
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("失败result==null");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().stopRefresh();
            }
        });
    }

    public void requestPermission() {
        Acp.getInstance(activity).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.CAMERA
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        getView().onPermissionGranted();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        getView().showToast(permissions.toString() + "权限拒绝");
                    }
                });
    }
}




