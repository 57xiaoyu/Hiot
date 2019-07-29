package com.cch.hiot2.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @Inject
    RegisterPresenter registerPresenter;//通过Dagger2注入RegisterPresenter
    private Unbinder unbinder;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_email)
    EditText etUserEmail;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;

    @OnClick(R.id.bt_register)
    void register() {
        String username = etUserName.getText().toString();
        String email = etUserEmail.getText().toString();
        String password = etUserPwd.getText().toString();
        //调用RegisterPresenter处理注册逻辑
        registerPresenter.register(username,email,password);
    }

    @OnClick(R.id.link_login)
    void toLogin() {
        //跳转到登录页面
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void injectDependencies() {
        //注册Dagger2  在ActivityComponent中添加对应的方法
        getActivityComponent().inject(this);
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return registerPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSucceed() {
        //注册成功，跳转到登录
        toLogin();
    }
}
