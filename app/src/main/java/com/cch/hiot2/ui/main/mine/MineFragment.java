package com.cch.hiot2.ui.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {

    @Inject
    MinePresenter minePresenter;

    @BindView(R.id.head_image)
    ImageView head_image;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_user_email)
    TextView tv_user_email;

    @Override
    protected MinePresenter createPresenter() {
        return minePresenter;
    }


    @OnClick(R.id.head_image)
    void headClick() {
        //showToast("headClick");
        minePresenter.changeHead();
    }

    @OnClick(R.id.ll_change_password)
    void changePassword() {
        showToast("changePassword");
    }

    @OnClick(R.id.ll_change_email)
    void changeEmail() {
        showToast("changeEmail");
    }

    @OnClick(R.id.ll_feedback)
    void feedback() {
        showToast("feedback");
    }

    @OnClick(R.id.ll_aboutus)
    void aboutUs() {
        showToast("aboutUs");
    }

    @OnClick(R.id.bt_logout)
    void logout() {
        //showToast("logout");
        minePresenter.logout();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        minePresenter.getUserInfo();
    }

    @Override
    protected void injectDependencies() {
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }

    private Unbinder unbinder;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showUserHead(String imgUrl) {
        Glide.with(this)
                .load(imgUrl)
                .into(head_image);
    }

    @Override
    public void showUserName(String username) {
        tv_user_name.setText(username);
    }

    @Override
    public void showUserEmail(String email) {
        tv_user_email.setText(email);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutSucceed() {
        getActivity().finish();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        minePresenter.onActivityResult(requestCode, resultCode, data);
    }
}














