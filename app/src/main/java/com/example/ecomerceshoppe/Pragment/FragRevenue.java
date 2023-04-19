package com.example.ecomerceshoppe.Pragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.Login;
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

import java.util.ArrayList;


public class FragRevenue extends Fragment  {
    PieChart pieChartOne,pieChartTwo,pieChartThree,pieChartFour;
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;
    RelativeLayout exportPDF;
    String nameShop="Nho Nam";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.layout_chart, container, false);
        mapping(view);

        setUpLineChart();
        setUpPieChart();





        setEvent();


        return view;
    }

    //piechart
    private  void setUpPieChart() {
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
        float[] yData = { 25, 40 };
        String[] xData = { "Áo", "2" };

        for (int i = 0; i < yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for (int i = 0; i < xData.length;i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private static void addDataSetTwo(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = { 25, 40 };
        String[] xData = { "Áo", "2" };

        for (int i = 0; i < yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for (int i = 0; i < xData.length;i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private static void addDataSetThree(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = { 25, 40 };
        String[] xData = { "Áo", "2" };

        for (int i = 0; i < yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for (int i = 0; i < xData.length;i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private static void addDataSetFour(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = { 25, 40 };
        String[] xData = { "Áo", "2" };

        for (int i = 0; i < yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for (int i = 0; i < xData.length;i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);


        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    //end piechart



    private  void setUpLineChart() {
        getEntriesLineChart();
        lineDataSet = new LineDataSet(lineEntries, "");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void getEntriesLineChart() {
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
                exportPDF.main(getContext(), nameShop.toLowerCase().trim().replace(" ",""));
                CustomToast.makeText(getContext(), "Xuất báo cáo thành công, vui lòng vào Tệp để xem báo cáo !", CustomToast.LENGTH_LONG, CustomToast.SUCCESS, true).show();

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


    }



}
