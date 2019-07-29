package com.cch.hiot2.ui.zxing;

import android.app.Activity;

import com.cch.hiot2.base.BasePresenter;
import com.cch.hiot2.http.HttpResult;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.ProgressDialogSubscriber;
import com.cch.hiot2.http.UserPreferencesHelper;

import javax.inject.Inject;

import rx.Observable;

public class ZxingPresenter extends BasePresenter <ZxingView>{

    @Inject
    HttpService service;
    @Inject
    Activity activity; @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public ZxingPresenter() {
    }

    public void addEquipment(String deviceId) {
        Observable<HttpResult> observable = service.addDevice(deviceId, preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult>(activity) {
            @Override
            public void onNext(HttpResult result) {
                if (result != null) {
                    if (result.getStatus() == 1) {
                        getView().showToast(result.getMsg());
                        //成功
                        getView().addSucceed();
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("失败result==null");
                }
            }
        });
    }
}
