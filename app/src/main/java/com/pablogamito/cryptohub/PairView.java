package com.pablogamito.cryptohub;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class PairView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pair_view_layout);
        getSupportActionBar().hide();

        // Get tradingPair object from extra_pair_id sent from other activity
        int pairId = Integer.parseInt( getIntent().getStringExtra("EXTRA_PAIR_ID") );
        CryptoHub app = ((CryptoHub)getApplicationContext());
        ArrayList<TradingPair> tradingPairs = app.getPairs();
        TradingPair tradingPair = tradingPairs.get(pairId);

        TextView pairName = (TextView) this.findViewById(R.id.pairName);
        TextView exchangeName = (TextView) this.findViewById(R.id.exchange);
        TextView price = (TextView) this.findViewById(R.id.price);
        TextView change = (TextView) this.findViewById(R.id.change);

        float priceVal = randomNum(1, 100);
        float changeVal = randomNum(-5, 5);
        float changePercentage = 100*priceVal/(priceVal-changeVal)-100;
        int color = changeVal >= 0 ? ContextCompat.getColor(this, R.color.mainGreen) : ContextCompat.getColor(this, R.color.mainRed);

        Typeface ralewayBold = Typeface.createFromAsset(this.getAssets(),"fonts/Raleway-Bold.ttf");
        Typeface sourceSansProRegular = Typeface.createFromAsset(this.getAssets(),"fonts/SourceSansPro-Regular.otf");
        Typeface sourceSansProLight = Typeface.createFromAsset(this.getAssets(),"fonts/SourceSansPro-Light.otf");
        Typeface sourceSansProSemibold = Typeface.createFromAsset(this.getAssets(),"fonts/SourceSansPro-Semibold.otf");

        pairName.setText(tradingPair.getName());
        pairName.setTypeface(sourceSansProSemibold);
        exchangeName.setText(tradingPair.getExchange());
        exchangeName.setTypeface(sourceSansProLight);
        price.setText("$" + String.format("%.02f", priceVal) );
        price.setTypeface(ralewayBold);
        if (changeVal >= 0) {
            change.setText("+$" + String.format("%.02f", changeVal) + " (" + String.format("%.02f",changePercentage) + "%)" );
        } else {
            change.setText("-$" + String.format("%.02f", abs(changeVal)) + " (" + String.format("%.02f",abs(changePercentage)) + "%)" );
        }
        change.setTextColor(color);
        change.setTypeface(sourceSansProRegular);

        // Draw Chart
        List<Entry> entries = new ArrayList<Entry>();
        View currentActivityView = findViewById(android.R.id.content);
        ((CryptoHub) this.getApplication()).loadChart(currentActivityView, R.id.chart, entries, ContextCompat.getColor(this, R.color.colorPrimary), 2);

    }

    public float randomNum(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public void changeChartTimeFrame(View view) {
        Button button;
        // Remove possible active button underline
        button = (Button) findViewById(R.id.dayView);
        button.setBackgroundResource(0);
        button = (Button) findViewById(R.id.monthView);
        button.setBackgroundResource(0);
        button = (Button) findViewById(R.id.monthView3);
        button.setBackgroundResource(0);
        button = (Button) findViewById(R.id.monthView6);
        button.setBackgroundResource(0);
        button = (Button) findViewById(R.id.yearView);
        button.setBackgroundResource(0);

        // Make clicked button active
        button = (Button) view;
        button.setBackground( ContextCompat.getDrawable(this, R.drawable.button_active));

        // TODO: Update chart

        // TODO: Update change timespan above chart to match selected
    }
}
