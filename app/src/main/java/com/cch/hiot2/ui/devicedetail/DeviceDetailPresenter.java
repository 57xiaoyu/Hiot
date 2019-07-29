package com.cch.hiot2.ui.devicedetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BasePresenter;
import com.cch.hiot2.entity.DeviceDetailEntity;
import com.cch.hiot2.http.HttpResult;
import com.cch.hiot2.http.HttpService;
import com.cch.hiot2.http.ProgressDialogSubscriber;
import com.cch.hiot2.http.UserPreferencesHelper;
import com.cch.hiot2.ui.datahistory.DataHistoryActivity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class DeviceDetailPresenter extends BasePresenter<DeviceDetailView> {

    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public DeviceDetailPresenter() {
    }

    //获取设备详情
    public void getDeviceDetail(String device_id) {
        Observable<HttpResult<DeviceDetailEntity>> observable
                = service.getDeviceDetail(device_id, preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult<DeviceDetailEntity>>(activity) {
            @Override
            public void onNext(HttpResult<DeviceDetailEntity> result) {
                getView().stopRefresh();
                if (result != null) {
                    if (result.getStatus() == 1 && result.getData() != null) {
                        //获取设备详情成功，处理请求结果
                        handleResult(result.getData());
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("登录失败result==null");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().stopRefresh();
            }
        });
    }

    private void handleResult(DeviceDetailEntity data) {
        //显示设备名称
        getView().showDeviceName("设备名字：" + data.getTitle());
        //显示设备状态
        if (data.getStatus() == 1) {
            getView().showDeviceStatus("设备状态 ：已激活");
        } else {
            getView().showDeviceStatus("设备状态 ：未激活");
        }
        //显示设备图片
        getView().showDeviceImage(HttpService.IMAGE_BASE_URL + data.getDeviceimg());
        //显示通道
        //添加之前先把之前的都删掉
        if (getView().getTongDaoGroupLayout() != null) {
            getView().getTongDaoGroupLayout().removeAllViews();
        }
        if (data.getUpdatastreamDataDtoList() != null && data.getUpdatastreamDataDtoList().size() > 0) {
            List<DeviceDetailEntity.UpdataStreamData> dtoList = data.getUpdatastreamDataDtoList();
            for (int i = 0; i < dtoList.size(); i++) {
                addToDaoItem(dtoList.get(i));
            }
        }
    }

    private void addToDaoItem(final DeviceDetailEntity.UpdataStreamData updatastreams) {
        if (getView().getTongDaoGroupLayout() == null) {
            getView().showToast("请实现getTongDaoGroupLayout方法返回LinearLayout以添加通道展示");
            return;
        }
        LinearLayout layout = getView().getTongDaoGroupLayout();
        View item = LayoutInflater.from(activity).inflate(R.layout.device_detial_tongdao_item, layout, false);
        layout.addView(item);
        View ll_root = item.findViewById(R.id.ll_root);
        ImageView value_look_more = item.findViewById(R.id.value_look_more);
        ImageView put_value = item.findViewById(R.id.put_value);
        TextView title = item.findViewById(R.id.title);
        TextView value = item.findViewById(R.id.value);
        final ImageView switch_img = item.findViewById(R.id.switch_img);
        final Switch switchs = item.findViewById(R.id.switchs);
        title.setText(updatastreams.getTitle());

        //updatastreams.getData_type() == 1     数值型通道
        //updatastreams.getData_type() == 2     布尔型、开关型数据
        //updatastreams.getData_type() == 3     GPS型数据
        //updatastreams.getData_type() == 4     文本型数据

        ll_root.setBackgroundColor(Color.parseColor("#414CA6"));
        if (updatastreams.getData_type() == 1) {
            ll_root.setBackgroundColor(Color.parseColor("#56B1E9"));
            value.setVisibility(View.VISIBLE);
            switch_img.setVisibility(View.GONE);
            switchs.setVisibility(View.GONE);
            put_value.setImageResource(R.mipmap.edit);
            //数值型通道
            if (updatastreams.getDataList() != null && updatastreams.getDataList().size() > 0) {
                //取集合中第一个数据
                DeviceDetailEntity.DataList dataList = updatastreams.getDataList().get(0);
                value.setText(dataList.getValue());
                setHistoryClick(value_look_more,updatastreams.getData_type(),dataList);
            } else {
                value.setText("--");
            }

            //判断通道为单向还是双向
            if (updatastreams.getDatastreamlink() == null) {
                //单向
                put_value.setVisibility(View.GONE);
            } else {
                //如果通道为双向的，开关按钮显示，可控制。
                put_value.setVisibility(View.VISIBLE);
                put_value.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getView().showToast("输入数值");
                    }
                });
            }
        } else if (updatastreams.getData_type() == 2) {
            ll_root.setBackgroundColor(Color.parseColor("#414CA6"));
            put_value.setVisibility(View.GONE);
            value.setVisibility(View.GONE);
            switch_img.setVisibility(View.VISIBLE);
            //布尔型、开关型数据
            if (updatastreams.getDataList() != null && updatastreams.getDataList().size() > 0) {
                //取集合中第一个数据
                DeviceDetailEntity.DataList dataList = updatastreams.getDataList().get(0);
                //判断开关状态，设置开关状态图片和开关按钮
                if (dataList.getStatus() == 0) {
                    switch_img.setImageResource(R.mipmap.off);
                    switchs.setChecked(false);
                } else if (dataList.getStatus() == 1) {
                    switch_img.setImageResource(R.mipmap.on);
                    switchs.setChecked(true);
                } else {
                    switch_img.setImageResource(R.mipmap.off);
                    switchs.setChecked(false);
                }
                setHistoryClick(value_look_more,updatastreams.getData_type(),dataList);
            }

            //如果通道为单向的，不可控制，开关按钮隐藏
            if (updatastreams.getDatastreamlink() == null) {
                //单向
                switchs.setVisibility(View.GONE);
            } else {
                //如果通道为双向的，开关按钮显示，可控制。
                switchs.setVisibility(View.VISIBLE);
                setSwitchListener(switchs, switch_img, updatastreams);

            }

        } else if (updatastreams.getData_type() == 3) {
            //GPS型数据
            ll_root.setBackgroundColor(Color.parseColor("#E9A760"));
            value.setTextSize(14f);
            value.setVisibility(View.VISIBLE);
            switch_img.setVisibility(View.GONE);
            switchs.setVisibility(View.GONE);
            put_value.setImageResource(R.mipmap.location_img);
            if (updatastreams.getDataList() != null && updatastreams.getDataList().size() > 0) {
                //取集合中第一个数据
                DeviceDetailEntity.DataList dataList = updatastreams.getDataList().get(0);
                value.setText("经度：" + dataList.getLongitude() + "\n纬度：" + dataList.getLatitude());
                setHistoryClick(value_look_more,updatastreams.getData_type(),dataList);
            } else {
                value.setText("暂无位置数据");
            }

            //判断通道为单向还是双向
            if (updatastreams.getDatastreamlink() == null) {
                //单向 不可控制
                put_value.setVisibility(View.GONE);
            } else {
                //如果通道为双向，显示，可控制。
                put_value.setVisibility(View.VISIBLE);
                put_value.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getView().showToast("GPS");
                    }
                });
            }
        } else if (updatastreams.getData_type() == 4) {
            //文本型数据
            ll_root.setBackgroundColor(Color.parseColor("#2B2B45"));
            value.setTextColor(Color.RED);
            value.setTextSize(14f);
            value.setVisibility(View.VISIBLE);
            switch_img.setVisibility(View.GONE);
            switchs.setVisibility(View.GONE);
            put_value.setImageResource(R.mipmap.edit);
            if (updatastreams.getDataList() != null && updatastreams.getDataList().size() > 0) {
                //取集合中第一个数据
                DeviceDetailEntity.DataList dataList = updatastreams.getDataList().get(0);
                value.setText(dataList.getNews());
                setHistoryClick(value_look_more,updatastreams.getData_type(),dataList);
            } else {
                value.setText("暂无警告");
            }

            //判断通道为单向还是双向
            if (updatastreams.getDatastreamlink() == null) {
                //单向 不可控制
                put_value.setVisibility(View.GONE);
            } else {
                //如果通道为双向，显示，可控制。
                put_value.setVisibility(View.VISIBLE);
                put_value.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getView().showToast("文本数据");
                    }
                });
            }
        } else {
            //未知类型
            value.setTextSize(14f);
            value.setVisibility(View.VISIBLE);
            switch_img.setVisibility(View.GONE);
            switchs.setVisibility(View.GONE);
            put_value.setVisibility(View.GONE);
            value.setText("未知通道请更新APP后查看");
        }
    }

    private void setHistoryClick(ImageView put_value,final int data_type,final DeviceDetailEntity.DataList data) {
        put_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DataHistoryActivity.class);
                intent.putExtra(DataHistoryActivity.DATA_TYPE_EXTRA,data_type);
                intent.putExtra(DataHistoryActivity.DATA_UPDATA_STREAM_ID_EXTRA,data.getUpDataStreamId());
                activity.startActivity(intent);
            }
        });
    }

    private void setSwitchListener(final Switch switchs, final ImageView switch_img,
                                   final DeviceDetailEntity.UpdataStreamData updatastreams) {
        //开关按钮切换状态
        switchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在向下通道的id不为空的情况下
                if (!TextUtils.isEmpty(updatastreams.getDatastreamlink().getDowndatastream().getId())) {
                    //开关被选中，则发送开的指令
                    if (switchs.isChecked()) {
                        switch_img.setImageResource(R.mipmap.on);
                        //发送通道id 和指令 1:代表开
                        postSwitch(updatastreams.getDatastreamlink().getDowndatastream().getId(), 1);
                    } else {
                        //发送向下通道的id和指令 0：代表关
                        switch_img.setImageResource(R.mipmap.off);
                        postSwitch(updatastreams.getDatastreamlink().getDowndatastream().getId(), 0);
                    }
                }
            }
        });
    }

    //控制设备
    private void postSwitch(String id, int status) {
        Observable<HttpResult> observable = service.postSwitch(id, status, preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult>(activity) {
            @Override
            public void onNext(HttpResult result) {
                if (result != null) {
                    if (result.getStatus() == 1) {
                        //成功
                        getView().showToast(result.getMsg());
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
