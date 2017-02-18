package com.pablogamito.cryptotradinghub;

import android.app.Application;
import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class CryptoTradingHub extends Application {
    // Generate all the pairs
    private ArrayList<Pair> pairs = new ArrayList<Pair>();

    @Override
    public void onCreate() {
        super.onCreate();
        // Add default pairs
        pairs.add( new Pair("BTC/CNY", "OkCoin") );
        pairs.add( new Pair("BTC/USD", "Bitfinex") );
        pairs.add( new Pair("BTC/USD", "Bitstamp") );
        pairs.add( new Pair("BTC/EUR", "Kraken") );
        pairs.add( new Pair("BTC/USD", "Btc-e") );
        pairs.add( new Pair("ETH/BTC", "Poloniex") );
        pairs.add( new Pair("ETH/BTC", "HitBTC") );
    }

    public ArrayList<Pair> getPairs() {
        return pairs;
    }

    public void addPairs(PairsArrayAdapter pairsArrayAdapter) {
        // Update changes in pair ArrayAdapter
        pairsArrayAdapter.notifyDataSetChanged();
    }

    public static void loadChart(View view, int chartId, List<Entry> entries, int color, float width) {
        // Draw Chart
        LineChart chart = (LineChart) view.findViewById(chartId);
        chart.setNoDataText("Unfortunately there was an error loading the data");
        chart.setViewPortOffsets(0f, 0f, 50f, 0f);
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
        chart.setScaleEnabled(false);
        chart.getLegend().setEnabled(false);   // Hide the legend
//        chart.animateX(200);

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

//        YourData[] dataObjects = ...;
//        for (YourData data : dataObjects) {
//            // turn data into Entry objects
//            entries.add(new Entry(data.getValueX(), data.getValueY()));
//        }

        float p = 500;
        for (int i=0; i<50; i++) {
            p = p-randomNum(-5, 5) ;
            entries.add(new Entry(i, p));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Price");
        dataSet.setColor(color); //33,206,153
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(width);
        dataSet.setDrawCircles(false);
        dataSet.setMode(LineDataSet.Mode.LINEAR);
        dataSet.setDrawHighlightIndicators(false);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }

    public static float randomNum(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

}
