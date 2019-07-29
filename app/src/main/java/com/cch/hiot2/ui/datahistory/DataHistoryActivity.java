package com.cch.hiot2.ui.datahistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cch.hiot2.R;
import com.cch.hiot2.base.BaseActivity;
import com.cch.hiot2.entity.DeviceDetailEntity;
import com.cch.hiot2.view.mpChart.MPChartHelper;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataHistoryActivity extends BaseActivity<DataHistoryView, DataHistoryPresenter> implements DataHistoryView {

    public static final String DATA_TYPE_EXTRA = "DATA_TYPE_EXTRA";
    public static final String DATA_UPDATA_STREAM_ID_EXTRA = "DATA_UPDATA_STREAM_ID_EXTRA";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.lineChart)
    LineChart lineChart;

    @Inject
    DataHistoryPresenter dataHistoryPresenter;
    private int data_type;
    private String updata_stream_id;

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected DataHistoryPresenter createPresenter() {
        return dataHistoryPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datahistory);
        ButterKnife.bind(this);
        initToolBar();
        initData();
    }

    private void initData() {
        data_type = getIntent().getIntExtra(DATA_TYPE_EXTRA, -1);
        updata_stream_id = getIntent().getStringExtra(DATA_UPDATA_STREAM_ID_EXTRA);

        dataHistoryPresenter.getDataHistory(updata_stream_id, data_type, 0, 500);
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
    public void showData(List<DeviceDetailEntity.DataList> data) {
        if (data != null && data.size() > 0) {
            if (data_type == 2) {
                ArrayList<String> xAxisValues = new ArrayList<>();//x轴
                ArrayList<Float> yAxisValues = new ArrayList<>();//点
                ArrayList<String> timing = new ArrayList<>();//时间
                for (int i = 0; i < data.size(); ++i) {
                    xAxisValues.add("" + i);
                    yAxisValues.add(Float.valueOf(data.get(i).getStatus()));//点的集合
                    timing.add(data.get(i).getTiming());
                }
                MPChartHelper.setLineChart(lineChart, xAxisValues, yAxisValues, timing, "电平图 0：代表关 1：代表开", true, true);
            } else if (data_type == 1) {
                lineChart.setNoDataText("数值数据暂未实现");
            } else if (data_type == 3) {
                lineChart.setNoDataText("GPS数据暂未实现");
            } else if (data_type == 4) {
                lineChart.setNoDataText("文本数据暂未实现");
            }
        } else {
            lineChart.setNoDataText("暂无历史数据");
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
