package com.cch.hiot2.ui.devicedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDetailActivity extends BaseActivity<DeviceDetailView, DeviceDetailPresenter>
        implements DeviceDetailView {

    public static final String DEVICE_ID_EXTRA = "device_id";

    @Inject
    DeviceDetailPresenter deviceDetailPresenter;

    @BindView(R.id.device_img)
    ImageView device_img;
    @BindView(R.id.device_name)
    TextView device_name;
    @BindView(R.id.device_state)
    TextView device_state;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_tongdao_group)
    LinearLayout ll_tongdao_group;

    private String device_id;

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected DeviceDetailPresenter createPresenter() {
        return deviceDetailPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        device_id = getIntent().getStringExtra(DEVICE_ID_EXTRA);
        ButterKnife.bind(this);

        initAppBarLayout();
        initView();
        //初始获取数据
        refreshData();
    }

    private void refreshData() {
        deviceDetailPresenter.getDeviceDetail(device_id);
    }

    private void initView() {
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_green_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark
        );
        //刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void initAppBarLayout() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle("设备详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeviceName(String showData) {
        device_name.setText(showData);
    }

    @Override
    public void showDeviceStatus(String showData) {
        device_state.setText(showData);
    }

    @Override
    public LinearLayout getTongDaoGroupLayout() {
        return ll_tongdao_group;
    }

    @Override
    public void showDeviceImage(String image_full_url) {
        Glide.with(this)
                .load(image_full_url)
                .into(device_img);
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
