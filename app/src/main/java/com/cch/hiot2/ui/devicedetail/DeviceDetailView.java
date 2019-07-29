package com.cch.hiot2.ui.devicedetail;

import android.widget.LinearLayout;

import com.cch.hiot2.base.BaseView;

public interface DeviceDetailView extends BaseView {
    void showToast(String msg);

    void showDeviceName(String showData);

    void showDeviceStatus(String showData);

    LinearLayout getTongDaoGroupLayout();

    void showDeviceImage(String image_full_url);

    void stopRefresh();
}












