package com.example.ecomerceshoppe.Pragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.SellerAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.Login;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Order;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.ExportPDF;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FragRevenue extends Fragment {

    Spinner thang, nam;
    ImageView searchRevenue;
    TextView nam_thang, slSanPham, top1, top2, top3, top4;
    PieChart pieChartOne, pieChartTwo, pieChartThree, pieChartFour;
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;
    RelativeLayout exportPDF;
    String nameShop = "Nho Nam", idShop;
    Double doanhthu = 0.0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_chart, container, false);
        mapping(view);
        //lấy dữ liệu đc gửi từ trang ManagerShop
        idShop = getArguments().getString("idUserCurent");
        setAdapter();


        try {
            getDoanhThuSetEvent();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return view;
    }

    private void getDoanhThuSetEvent() throws JSONException {
        SellerAPI.getOrderByShop(getContext(), idShop, new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                JSONArray dataArr = (JSONArray) response.get("data");
                JSONObject data; //item data in dataArr

                doanhthu = 0.0;
                for (int i = 0; i < dataArr.length(); i++) {
                    data = (JSONObject) dataArr.get(i);

                    doanhthu += data.getDouble("total_amount");
//                    orderTmp.setCreatAtOrder(data.getString("createdAt"));

                }

                setUpLineChart();
                setUpPieChart();

                setEvent();
//                setEvent();
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }


    private void setAdapter() {
        List<String> thangList = new ArrayList<String>();
        thangList.add("Tháng 1");
        thangList.add("Tháng 2");
        thangList.add("Tháng 3");
        thangList.add("Tháng 4");
        thangList.add("Tháng 5");
        thangList.add("Tháng 6");
        thangList.add("Tháng 7");
        thangList.add("Tháng 8");
        thangList.add("Tháng 9");
        thangList.add("Tháng 10");
        thangList.add("Tháng 11");
        thangList.add("Tháng 12");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, thangList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        thang.setAdapter(dataAdapter);

        List<String> namList = new ArrayList<String>();
        namList.add("2018");
        namList.add("2019");
        namList.add("2020");
        namList.add("2021");
        namList.add("2022");
        namList.add("2023");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, namList);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        nam.setAdapter(dataAdapter1);

    }

    //piechart
    private void setUpPieChart() {
        pieChartOne.setRotationEnabled(true);
        pieChartOne.setDescription(new Description());
        pieChartOne.setHoleRadius(35f);
        pieChartOne.setTransparentCircleAlpha(0);
        pieChartOne.setCenterText("50%");
        pieChartOne.setCenterTextSize(10);
        pieChartOne.setDrawEntryLabels(true);

        pieChartTwo.setRotationEnabled(true);
        pieChartTwo.setDescription(new Description());
        pieChartTwo.setHoleRadius(35f);
        pieChartTwo.setTransparentCircleAlpha(0);
        pieChartTwo.setCenterText("50%");
        pieChartTwo.setCenterTextSize(10);
        pieChartTwo.setDrawEntryLabels(true);

        pieChartThree.setRotationEnabled(true);
        pieChartThree.setDescription(new Description());
        pieChartThree.setHoleRadius(35f);
        pieChartThree.setTransparentCircleAlpha(0);
        pieChartThree.setCenterText("50%");
        pieChartThree.setCenterTextSize(10);
        pieChartThree.setDrawEntryLabels(true);

        pieChartFour.setRotationEnabled(true);
        pieChartFour.setDescription(new Description());
        pieChartFour.setHoleRadius(35f);
        pieChartFour.setTransparentCircleAlpha(0);
        pieChartFour.setCenterText("50%");
        pieChartFour.setCenterTextSize(10);
        pieChartFour.setDrawEntryLabels(true);

        addDataSetOne(pieChartOne);
        addDataSetTwo(pieChartTwo);
        addDataSetThree(pieChartThree);
        addDataSetFour(pieChartFour);
    }

    private static void addDataSetOne(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = {25, 75};
        String[] xData = {"Áo", "2"};

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for (int i = 0; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private static void addDataSetTwo(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = {25, 40};
        String[] xData = {"Áo", "2"};

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for (int i = 0; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private static void addDataSetThree(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = {25, 40};
        String[] xData = {"Áo", "2"};

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for (int i = 0; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private static void addDataSetFour(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = {25, 40};
        String[] xData = {"Áo", "2"};

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for (int i = 0; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    //end piechart


    private void setUpLineChart() {
        getEntriesLineChart();
        lineDataSet = new LineDataSet(lineEntries, "");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void getEntriesLineChart() {

//        List<Integer> list = new ArrayList<Integer>();
//        for (int i = 1; i <= 12; i++) {
//            list.add(i);
//        }
//        Collections.shuffle(list);
//        System.out.println(list);

        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1f, 0));
        lineEntries.add(new Entry(2f, 1));
        lineEntries.add(new Entry(3f, 7));
        lineEntries.add(new Entry(4f, 5));
        lineEntries.add(new Entry(5f, 2));
        lineEntries.add(new Entry(6f, 7));
        lineEntries.add(new Entry(7f, 1));
        lineEntries.add(new Entry(8f, 5));
        lineEntries.add(new Entry(9f, 7));
        lineEntries.add(new Entry(10f, 2));
        lineEntries.add(new Entry(11f, 9));
        lineEntries.add(new Entry(12f, 1));
    }

    private void setEvent() {
        exportPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExportPDF exportPDF = new ExportPDF();
                exportPDF.main(getContext(), "Baocao" + thang.getSelectedItem() + "-" + nam.getSelectedItem());
                CustomToast.makeText(getContext(), "Xuất báo cáo thành công, vui lòng vào Tệp để xem báo cáo !", CustomToast.LENGTH_LONG, CustomToast.SUCCESS, true).show();

            }
        });

        slSanPham.setText(String.valueOf(doanhthu));

        nam_thang.setText(thang.getSelectedItem() + " " + "Năm " + nam.getSelectedItem());
        searchRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nam_thang.setText(thang.getSelectedItem() + " " + "Năm " + nam.getSelectedItem());
            }
        });
    }

    private void mapping(View view) {
        lineChart = view.findViewById(R.id.lineChart);
        pieChartOne = view.findViewById(R.id.piechartOne);
        pieChartTwo = view.findViewById(R.id.piechartTwo);
        pieChartThree = view.findViewById(R.id.piechartThree);
        pieChartFour = view.findViewById(R.id.piechartFour);
        exportPDF = view.findViewById(R.id.exportPDF);
        thang = view.findViewById(R.id.thang);
        nam = view.findViewById(R.id.nam);
        nam_thang = view.findViewById(R.id.nam_thang);
        slSanPham = view.findViewById(R.id.spCount);
        top1 = view.findViewById(R.id.topone);
        top2 = view.findViewById(R.id.toptwo);
        top3 = view.findViewById(R.id.topthree);
        top4 = view.findViewById(R.id.topfour);
        searchRevenue = view.findViewById(R.id.search_revenue);


    }


}
