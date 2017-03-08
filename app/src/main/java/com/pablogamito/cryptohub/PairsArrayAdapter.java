package com.pablogamito.cryptohub;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

class PairsArrayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TradingPair> tradingPairs;

    public PairsArrayAdapter(Context context, ArrayList<TradingPair> tradingPairs) {
        this.context = context;
        this.tradingPairs = tradingPairs;
    }

    public TradingPair getPair(int id) {
        return tradingPairs.get(id);
    }

    @Override
    public int getCount() {
        return tradingPairs.size();
    }

    @Override
    public Object getItem(int position) {
        return tradingPairs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.pair_list_layout, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView exchange = (TextView) convertView.findViewById(R.id.exchange);
        TextView price = (TextView) convertView.findViewById(R.id.price);

        Typeface ralewayRegular = Typeface.createFromAsset(context.getAssets(),"fonts/Raleway-Regular.ttf");
        Typeface sourceSansProSemibold = Typeface.createFromAsset(context.getAssets(),"fonts/SourceSansPro-Semibold.otf");
        Typeface sourceSansProLight = Typeface.createFromAsset(context.getAssets(),"fonts/SourceSansPro-Light.otf");

        float priceChange = com.pablogamito.cryptohub.CryptoHub.randomNum(-10, 10);
        float priceVal = com.pablogamito.cryptohub.CryptoHub.randomNum(1, 100);
        int color = priceChange >= 0 ? ContextCompat.getColor(context, R.color.mainGreen) : ContextCompat.getColor(context, R.color.mainRed);

        name.setText(tradingPairs.get(position).getName());
        name.setTypeface(sourceSansProSemibold);
        exchange.setText(tradingPairs.get(position).getExchange());
        exchange.setTypeface(sourceSansProLight);
        price.setTypeface(ralewayRegular);
        price.setText("$" + String.format("%.02f",priceVal));
//        price.setTextColor(color);

        // Draw Chart
        List<Entry> entries = new ArrayList<Entry>();
        com.pablogamito.cryptohub.CryptoHub.loadChart(convertView, R.id.chart, entries, color, 1.5f);

        return convertView;
    }
}
