package com.cch.hiot2.ui.main.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.base.BaseFragment;

import javax.inject.Inject;

public class MessageFragment extends BaseFragment<MessageView, MessagePresenter> implements MessageView {
    @Inject
    MessagePresenter messagePresenter;

    @Override
    protected MessagePresenter createPresenter() {
        return messagePresenter;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ImageView imageView = view.findViewById(R.id.iv_image);
        Glide.with(this)
                .load("https://static.oschina.net/uploads/space/2017/0325/085809_5wvz_2945455.png")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .override(100,100)
                .centerCrop()
                .crossFade()
                .into(imageView);


        return view;
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }
}


