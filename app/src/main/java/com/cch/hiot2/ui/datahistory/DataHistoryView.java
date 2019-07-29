package com.cch.hiot2.ui.datahistory;

import com.cch.hiot2.base.BaseView;
import com.cch.hiot2.entity.DeviceDetailEntity;

import java.util.List;

public interface DataHistoryView  extends BaseView{
    void showData(List<DeviceDetailEntity.DataList> data);

    void showToast(String msg);
}
