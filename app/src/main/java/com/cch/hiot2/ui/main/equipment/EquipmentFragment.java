package com.cch.hiot2.ui.main.equipment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.base.BaseFragment;
import com.cch.hiot2.entity.HolderDeviceEntity;
import com.cch.hiot2.ui.devicedetail.DeviceDetailActivity;
import com.cch.hiot2.ui.zxing.ZxingActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EquipmentFragment extends BaseFragment<EquipmentView, EquipmentPresenter>
        implements EquipmentView, DeviceAdapter.OnItemClickListener {

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.tv_nodata)
    TextView tv_nodata;

    @Inject
    EquipmentPresenter equipmentPresenter;
    private DeviceAdapter adapter;

    @Override
    protected EquipmentPresenter createPresenter() {
        return equipmentPresenter;
    }

    @OnClick(R.id.iv_add_device)
    void addDevice() {
        //TODO:添加设备
        //showToast("添加设备");
        equipmentPresenter.requestPermission();
    }

    @Override
    public void onResume() {
        super.onResume();
        //重新获取列表
        equipmentPresenter.getDeviceList();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        equipmentPresenter.getDeviceList();
    }

    private void initViews() {
        tv_nodata.setVisibility(View.VISIBLE);
        tv_nodata.setText("暂无设备哦~，马上去绑定吧");

        //设置刷新进度条颜色
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_green_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark
        );
        //设置下拉刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //网络请求
                equipmentPresenter.getDeviceList();
            }
        });
        //设置布局管理器为LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置适配器
        adapter = new DeviceAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        //设置点击事件
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshData(List<HolderDeviceEntity> data) {
        if (data == null || data.size() == 0) {
            tv_nodata.setVisibility(View.VISIBLE);
        } else {
            tv_nodata.setVisibility(View.GONE);
        }
        adapter.resetDataAndNotifyDataSetChanged(data);
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onPermissionGranted() {
        //打开扫码页面
        Intent intent = new Intent(getActivity(), ZxingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, HolderDeviceEntity item) {
        //showToast("onItemClick :" + position);
        //TODO:跳转到设备详情页
        Intent intent = new Intent(getActivity(), DeviceDetailActivity.class);
        intent.putExtra(DeviceDetailActivity.DEVICE_ID_EXTRA,item.getId());
        startActivity(intent);
    }
}














