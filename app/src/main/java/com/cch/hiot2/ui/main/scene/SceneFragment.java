package com.cch.hiot2.ui.main.scene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.base.BaseFragment;

import javax.inject.Inject;

public class SceneFragment extends BaseFragment<SceneView, ScenePresenter> implements SceneView{

    @Inject
    ScenePresenter scenePresenter;

    @Override
    protected ScenePresenter createPresenter() {
        return scenePresenter;
    }

    @Override
    protected void injectDependencies() {
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scene, container, false);
        return view;
    }
}






