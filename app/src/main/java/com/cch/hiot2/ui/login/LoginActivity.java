package com.cch.hiot2.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.ui.main.MainActivity;
import com.cch.hiot2.ui.register.RegisterActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @Inject
    LoginPresenter loginPresenter;
    private Unbinder unbinder;

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;

    @OnClick(R.id.bt_login)
    void login() {
        String username = etUserName.getText().toString();
        String password = etUserPwd.getText().toString();
        loginPresenter.login(username, password);
    }

    @OnClick(R.id.forget_password)
    void forgetPassword() {
        //TODO:忘记密码
        Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.link_signup)
    void linkSignup() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return loginPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        //如果保存了用户名密码，即已经登陆过，则自动登录
        loginPresenter.autoLogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSucceed() {
        //登录成功 跳转到MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}














