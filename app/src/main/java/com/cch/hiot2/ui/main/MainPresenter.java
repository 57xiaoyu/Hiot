package com.cch.hiot2.ui.main;

import android.app.Activity;

import com.cch.hiot2.base.BasePresenter;
import com.cch.hiot2.entity.LoginEntity;
import com.cch.hiot2.http.HttpResultFunc;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.ProgressDialogSubscriber;
import com.cch.hiot2.http.UserPreferencesHelper;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class MainPresenter extends BasePresenter<MainView> {
    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public MainPresenter() { }

    public void login(final String username,final String password, String loginCode) {
        Observable<LoginEntity> observable = service.login(username, password, loginCode)
                .map(new HttpResultFunc<LoginEntity>());
        toSubscribe(observable, new ProgressDialogSubscriber<LoginEntity>(activity){
            @Override
            public void onNext(LoginEntity loginEntity) {
                getView().showToast(loginEntity.getUuid());
                //登录成功，保存Token 和UUID
                preferencesHelper.putTokenValue(loginEntity.getTokenValue());
                preferencesHelper.putUuid(loginEntity.getUuid());
                //保存登录的用户名和密码
                preferencesHelper.putUserName(username);
                preferencesHelper.putPassword(password);
            }
        });
    }
}






