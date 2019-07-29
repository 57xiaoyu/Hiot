package com.cch.hiot2.view.mpChart;

import android.graphics.Color;
import android.widget.Toast;

import com.cch.hiot2.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *工具类
 */

public class MPChartHelper {

    public static final int[] LINE_COLORS = {
            Color.rgb(140, 210, 118), Color.rgb(159, 143, 186), Color.rgb(233, 197, 23)
    };//绿色，紫色，黄色

    public static final int[] LINE_FILL_COLORS = {
            Color.rgb(222, 239, 228), Color.rgb(246, 234, 208), Color.rgb(235, 228, 248)
    };

    /**
     * 单线单Y轴
     * @param lineChart
     * @param xAxisValue x轴的值
     * @param yAxisValue y轴的值
     * @param timing 时间
     * @param title 每一个数据系列标题
     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
     * @param isSwitch 是否是开关类型，true：展示电平图 false:折线图
     */
    public static void setLineChart(LineChart lineChart, List<String> xAxisValue, List<Float> yAxisValue, List<String> timing, String title, boolean showSetValues, boolean isSwitch) {
        //线数据集合，List<Float>代表一条线的y轴数据集合
        List<List<Float>> entriesList = new ArrayList<>();
        //这里只有一条线，一个数据集
        entriesList.add(yAxisValue);
        //图例数据集
        List<String> titles = new ArrayList<>();
        //只有一条线，一个图例
        titles.add(title);

        setLinesChartStyle(lineChart, xAxisValue, entriesList, timing, titles, showSetValues, null, isSwitch);
    }

    /**
     * 绘制线图，默认最多绘制三种颜色。所有线均依赖左侧y轴显示。
     *
     * @param lineChart
     * @param xAxisValue    x轴的值
     * @param yXAxisValues  y轴的值
     * @param titles        每一个数据系列的标题
     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
     * @param lineColors    线的颜色数组。为null时取默认颜色，此时最多绘制三种颜色。
     * @param isSwitch 是否是开关类型，true：展示电平图 false:折线图
     */
    public static void setLinesChartStyle(final LineChart lineChart, List<String> xAxisValue, List<List<Float>> yXAxisValues, final List<String> timing, List<String> titles, boolean showSetValues, int[] lineColors, boolean isSwitch) {
        lineChart.getDescription().setEnabled(false);//设置不显示描述
        lineChart.setPinchZoom(true);//设置按比例放折线图
        //添加marker
        MPChartMarkerView markerView = new MPChartMarkerView(lineChart.getContext(), R.layout.custom_marker_view);
        lineChart.setMarker(markerView);
        //设置点击事件,弹出x轴每一项的值
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            //弹出每个点对应的时间
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(lineChart.getContext(), timing.get((int) e.getX()), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() {

            }
        });

        //x坐标轴设置
        XAxis xAxis = lineChart.getXAxis();
        //X轴在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //不绘制x轴网格
        xAxis.setDrawGridLines(false);
        //设置最小间隔，防止当放大时，出现重复标签
        xAxis.setGranularity(1f);
        //设置x轴标签个数
        xAxis.setLabelCount(xAxisValue.size());

        //左侧y轴设置
        YAxis leftAxis = lineChart.getAxisLeft();
        //y轴标签绘制的位置
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //不绘制y轴网格
        leftAxis.setDrawGridLines(false);
        if (showSetValues) {
            leftAxis.setDrawLabels(false);//折线上显示值，则不显示坐标轴上的值
        }
        //设置不显示右边的Y轴
        lineChart.getAxisRight().setEnabled(false);
        //图例设置
        Legend legend = lineChart.getLegend();
        //设置图例位置 chart的正上方，水平显示
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        //设置图例位置 chart的正上方，水平显示
        legend.setForm(Legend.LegendForm.LINE);
        //设置图例文字大小
        legend.setTextSize(12f);

        //设置折线图数据
        setLinesChartData(lineChart, yXAxisValues, titles, showSetValues, lineColors, isSwitch);

        //设置视图窗口大小
        lineChart.setExtraOffsets(10, 30, 20, 10);
        //可触摸
        lineChart.setTouchEnabled(true);
        // 可拖曳
        lineChart.setDragEnabled(true);
         //一个界面最多显示6个点，其他点可以通过滑动看到
        lineChart.setVisibleXRangeMaximum(5);
        //一个页面最少显示4个点
        lineChart.setVisibleXRangeMinimum(3);
        // 可缩放
        lineChart.setScaleEnabled(true);
        //数据显示动画，从左往右依次显示
        lineChart.animateX(1000);




    }

    private static void setLinesChartData(LineChart lineChart, List<List<Float>> yXAxisValues, List<String> titles, boolean showSetValues, int[] lineColors, boolean isSwitch) {
        //Entry是一个（x,y）对， entries可以理解为一条线上的点集合，entriesList可以理解为线集合
        List<List<Entry>> entriesList = new ArrayList<>();
        //循环遍历线集合
        for (int i = 0; i < yXAxisValues.size(); ++i) {
            //1条线
            ArrayList<Entry> entries = new ArrayList<>();
            //循环遍历
            for (int j = 0, n = yXAxisValues.get(i).size(); j < n; j++) {
                //Entry:点实体  j表示X轴坐标， yXAxisValues.get(i).get(j)表示Y轴坐标
                entries.add(new Entry(j, yXAxisValues.get(i).get(j)));
            }
            entriesList.add(entries);
        }
        //图表数据不为空
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {

            for (int i = 0; i < lineChart.getData().getDataSetCount(); ++i) {
                //LineDataSet可以看做是一条线
                LineDataSet set = (LineDataSet) lineChart.getData().getDataSetByIndex(i);
                //为每条线设置数据
                set.setValues(entriesList.get(i));
                //设置图例
                set.setLabel(titles.get(i));
                // 设置该DataSet的线宽（最小= 0.2F，最大= 10F）；默认1F；注意：线越细,性能越好，线越宽，性能越差 。
//                set.setLineWidth(1.75f); // 线宽
                //设置线圈的大小（半径），默认为 4F
//                set.setCircleSize(5f);// 显示的圆形大小
            }
           //通知数据改变，刷新图表
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
          //图表数据为空，初始化
        } else {
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();

            for (int i = 0; i < entriesList.size(); ++i) {
                LineDataSet set = new LineDataSet(entriesList.get(i), titles.get(i));
//                set.setLineWidth(1.75f); // 线宽
//                set.setCircleSize(5f);
                if (isSwitch) {
                    //设置为方型折线
                    set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                            ? LineDataSet.Mode.LINEAR
                            : LineDataSet.Mode.STEPPED);
                }
                if (lineColors != null) {
                    //设置线的颜色
                    set.setColor(lineColors[i % entriesList.size()]);
                    //设置线圈的颜色。
                    set.setCircleColor(lineColors[i % entriesList.size()]);
                    //设置线圈的内圆（孔）的颜色。
                    set.setCircleColorHole(Color.WHITE);
                } else {
                    set.setColor(LINE_COLORS[i % 3]);
                    set.setCircleColor(LINE_COLORS[i % 3]);
                    set.setCircleColorHole(Color.WHITE);
                }
//                if (entriesList.size() == 1) {
//                 //默认值为false,设置为true:数据集应该画满（表面区域），注意：禁止这将给予极大的性能提升！
//                    set.setDrawFilled(false);
//                    //设置填充颜色
//                    set.setFillColor(LINE_FILL_COLORS[i % 3]);
//                }
                dataSets.add(set);
            }

            LineData data = new LineData(dataSets);
            if (showSetValues) {
               // 设置DataSet数据对象包含的数据的值文本的大小（单位是dp）。
                data.setValueTextSize(10f);
                //为LineData数据对象包含的数据设置自定义的ValueFormatter 。
                data.setValueFormatter(new IValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
                        return MPChartMarkerView.double2String(value, 1);
                    }
                });
            } else {
                //启用/禁用 绘制所有 DataSets 数据对象包含的数据的值文本。
                data.setDrawValues(false);
            }
            //为折线图设置数据
            lineChart.setData(data);
        }
    }


}
