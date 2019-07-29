package com.cch.hiot2.ui.register;

import android.app.Activity;
import android.text.TextUtils;

import com.cch.hiot2.base.BasePresenter;
import com.cch.hiot2.entity.RegisterEntity;
import com.cch.hiot2.http.HttpResult;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.ProgressDialogSubscriber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import rx.Observable;

public class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    public RegisterPresenter() {}
    public void register(String username, String email, String password) {
        if (TextUtils.isEmpty(username)) {
            getView().showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            getView().showToast("请输入邮箱");
            return;
        }
        if (!isEmail(email)) {
            getView().showToast("邮箱格式不正确");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            getView().showToast("请输入密码");
            return;
        }
        Observable<HttpResult<RegisterEntity>> observable = service.register(username, email, password, "1");
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult<RegisterEntity>>(activity) {
            @Override
            public void onNext(HttpResult<RegisterEntity> result) {
                if (result != null) {
                    if (result.getStatus() == 1) {
                        getView().registerSucceed();
                        getView().showToast(result.getMsg());
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("注册失败result==null");
                }
            }
        });
    }

    /**
     * 判断email格式是否正确
     *
     * @param email 邮箱
     */
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
