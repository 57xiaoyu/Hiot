package com.cch.hiot2.ui.main.equipment;

import com.cch.hiot2.base.BaseView;
import com.cch.hiot2.entity.HolderDeviceEntity;

import java.util.List;

public interface EquipmentView extends BaseView{
    void showToast(String msg);
    void refreshData(List<HolderDeviceEntity> data);
    void stopRefresh();

    void onPermissionGranted();
}



