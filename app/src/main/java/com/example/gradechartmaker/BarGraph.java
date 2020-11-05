package com.example.gradechartmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarGraph extends AppCompatActivity {

    BarChart barChart;//creates bar chart
    String letterGrade;//holds the letter grade for the x-label
    float percent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);
        //array lists created
        ArrayList<BarEntry>barEntryArrayList=new ArrayList<>();
        ArrayList<String>labelNames=new ArrayList<>();
        //linking the View
        barChart=(BarChart)findViewById(R.id.barGraph);//link the BarChart View from layout to java
        //BarDataSet dataSet=new BarDataSet(GradeInputActivity.percentArr,"Percents");
        String [] letterGrades=new String[]{"As(%)","Bs(%)","Cs(%)","Ds(%)","Fs(%)"};
        //This loop sets the percentage and labels to arrayLists that will be passed into graph
        for(int i=0;i<GradeInputActivity.percentArr.length;i++){
            letterGrade=letterGrades[i];
            percent=GradeInputActivity.percentArr[i];
            barEntryArrayList.add((new BarEntry(i,percent)));
            labelNames.add(letterGrade);
        }
        //contains the data set ans it is initialized to barData
        BarDataSet barDataSet=new BarDataSet(barEntryArrayList,"Students' Grade Distribution");
        BarData barData=new BarData(barDataSet);
        //set different colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Sets the description to blank
        Description description=new Description();
        description.setText("");
        barChart.setDescription(description);
        //change the text size
        barData.setValueTextSize(15f);
        //executes the bar graph
        barChart.setData(barData);
        YAxis axisY=barChart.getAxis(YAxis.AxisDependency.LEFT);
        XAxis axisX=barChart.getXAxis();
        //Sets the labels for the x-axis
        axisX.setValueFormatter(new IndexAxisValueFormatter(labelNames));
        //setting the x-axis on the bottom
        axisX.setPosition(XAxis.XAxisPosition.BOTTOM);
        //delete grid lines
        axisX.setDrawGridLines(false);

        axisX.setLabelCount(labelNames.size());
        //allows interactivity with the graph
        barChart.setTouchEnabled(true);
        //delayed in animation upon activity startup
        barChart.animateY(3000);
        //barChart.invalidate();
    }
}