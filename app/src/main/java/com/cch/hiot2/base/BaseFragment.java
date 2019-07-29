package com.cch.hiot2.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment <V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {
    private P mvpPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=initView(inflater, container, savedInstanceState);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        mvpPresenter = createPresenter();
        if(mvpPresenter!=null) {
            mvpPresenter.setView((V) this);
        }
        super.onViewCreated(view, savedInstanceState);
    }
    protected void injectDependencies() {}
    protected abstract P createPresenter();
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mvpPresenter!=null){
            mvpPresenter.detachView();
        }
    }
}

