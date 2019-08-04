package com.cch.hiot2.ui.datahistory;

import android.app.Activity;

import com.cch.hiot2.base.BasePresenter;
import com.cch.hiot2.entity.DeviceDetailEntity;
import com.cch.hiot2.http.HttpResult;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.ProgressDialogSubscriber;
import com.cch.hiot2.http.UserPreferencesHelper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class DataHistoryPresenter extends BasePresenter<DataHistoryView> {
    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public DataHistoryPresenter() {
    }

    public void getDataHistory(String updata_stream_id, int data_type, int index, int limit) {

        Observable<HttpResult<List<DeviceDetailEntity.DataList>>> observable= service.getDataHistory(
                data_type,
                updata_stream_id,
                index,
                limit,
                preferencesHelper.getTokenValue()
        );
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult<List<DeviceDetailEntity.DataList>>>(activity) {
            @Override
            public void onNext(HttpResult<List<DeviceDetailEntity.DataList>> result) {
                if (result != null) {
                    if (result.getStatus() == 1 && result.getData() != null) {
                        //获取设备详情成功，处理请求结果
                        getView().showData(result.getData());
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("失败result==null");
                }
            }
        });
    }
}
