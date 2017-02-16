package com.pablogamito.cryptotradinghub;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class pairView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_view);
        // Get pair object from extra_pair_id sent from other activity
        int pairId = Integer.parseInt( getIntent().getStringExtra("EXTRA_PAIR_ID") );
        ArrayList<Pair> pairs = ((CryptoTradingHub) this.getApplication()).getPairs();
        Pair pair = pairs.get(pairId);

        TextView price = (TextView) this.findViewById(R.id.price);
        TextView pairName = (TextView) this.findViewById(R.id.pairName);
        TextView exchangeName = (TextView) this.findViewById(R.id.exchange);

        pairName.setText(pair.getName());
        exchangeName.setText(pair.getExchange());

        // Draw Chart
        LineChart chart = (LineChart) findViewById(R.id.chart);
        chart.setNoDataText("Unfortunately there was an error loading the data");
//        chart.setDescription("");
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setDrawBorders(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getLegend().setEnabled(false);   // Hide the legend
//        chart.animateX(200);

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

//        YourData[] dataObjects = ...;
        List<Entry> entries = new ArrayList<Entry>();
//        for (YourData data : dataObjects) {
//            // turn data into Entry objects
//            entries.add(new Entry(data.getValueX(), data.getValueY()));
//        }

        float p = 500;
        for (int i=0; i<100; i++) {
            p = p-randomNum(-5, 5) ;
            entries.add(new Entry(i, p));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Price");
        dataSet.setColor(Color.rgb(71,187,150)); //33,206,153
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(2);
        dataSet.setDrawCircles(false);
        dataSet.setMode(LineDataSet.Mode.LINEAR);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh

    }

    public float randomNum(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
