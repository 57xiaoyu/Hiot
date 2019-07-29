package com.cch.hiot2.ui.gpsdata;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.entity.DeviceDetailEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GpsHistoryActivity extends BaseActivity<GpsHistoryView, GpsHistoryPresenter> implements GpsHistoryView, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, GeocodeSearch.OnGeocodeSearchListener {

    public static final String DATA_TYPE_EXTRA = "DATA_TYPE_EXTRA";
    public static final String DATA_UPDATA_STREAM_ID_EXTRA = "DATA_UPDATA_STREAM_ID_EXTRA";

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.title)TextView title;
    @BindView(R.id.map)MapView mapView;

    @Inject
    GpsHistoryPresenter gpsHistoryPresenter;

    private int data_type;
    private String updata_stream_id;

    private AMap aMap;//地图对象
    private LatLonPoint latLonPoint;//终点
    private GeocodeSearch geocoderSearch;
    private String addressName;
    private MarkerOptions markerOption;
    private String locationTime;

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected GpsHistoryPresenter createPresenter() {
        return gpsHistoryPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_history);
        ButterKnife.bind(this);
        data_type = getIntent().getIntExtra(DATA_TYPE_EXTRA, -1);
        updata_stream_id = getIntent().getStringExtra(DATA_UPDATA_STREAM_ID_EXTRA);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState); // 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        initToolBar();
        initMap();
    }

    private void initMap() {
//获取地图对象
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            //设置比例尺默认显示
            settings.setScaleControlsEnabled(true);
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            //指南针
            settings.setCompassEnabled(true);
            aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
            aMap.setOnMarkerClickListener(this);
            aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
            geocoderSearch = new GeocodeSearch(this);
            geocoderSearch.setOnGeocodeSearchListener(this);
            //网络请求
            getGpsHistoryData();
        }
    }

    private void getGpsHistoryData() {
        gpsHistoryPresenter.getGpsHistoryData(updata_stream_id, data_type, 0, 100);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    /**
     * marker 对象被点击时回调的接口
     * @param marker
     * @return 返回 true 则表示接口已响应事件，否则返回false
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        //逆地理位置编码
        getAddress(marker.getPosition().latitude, marker.getPosition().longitude);
        return false;
    }
    /**
     * 逆地理编码
     * @param v 纬度
     * @param v1 经度
     */
    private void getAddress(double v, double v1) {
        this.latLonPoint = new LatLonPoint(v, v1);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 100,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
    }

    /**
     * 画Marker
     * @param v
     * @param v1
     * @param time
     */
    private void drawMarker(double v, double v1, String time) {
        locationTime = time;
        markerOption = new MarkerOptions();
        markerOption.position(new LatLng(v, v1));
        markerOption.title("定位时间:" + locationTime);
        markerOption.draggable(true);
        markerOption.setFlat(true);
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        aMap.addMarker(markerOption);
    }
    /**
     * 逆地理位置编码回调
     * @param result
     * @param rCode
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                addressName = result.getRegeocodeAddress().getFormatAddress();
                Toast.makeText(this, addressName, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "没有查询到结果", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, rCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
    }

    /**
     * 设置点击infoWindow，隐藏Marker窗口
     * @param marker
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        //隐藏窗口
        marker.hideInfoWindow();
    }

    @Override
    public void showData(List<DeviceDetailEntity.DataList> data) {
        //点集合
        List<LatLng> latLngs = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                DeviceDetailEntity.DataList item = data.get(i);
                //画maker
                drawMarker(item.getLatitude(), item.getLongitude(), data.get(i).getTiming());
                //画点
                latLngs.add(new LatLng(item.getLatitude(), item.getLongitude()));
            }
            //画线
            aMap.addPolyline(new PolylineOptions().addAll(latLngs).geodesic(true).color(Color.RED));
            //移动到第一个点
            DeviceDetailEntity.DataList point1 = data.get(0);

            aMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                    new CameraPosition(new LatLng(point1.getLatitude(), point1.getLongitude()), 16, 0, 0)));

        }

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
