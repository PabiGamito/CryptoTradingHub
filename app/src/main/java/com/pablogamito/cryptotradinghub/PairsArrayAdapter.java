package com.pablogamito.cryptotradinghub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class PairsArrayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Pair> pairs;

    public PairsArrayAdapter(Context context, ArrayList<Pair> pairs) {
        this.context = context;
        this.pairs = pairs;
    }

    public Pair getPair(int id) {
        return pairs.get(id);
    }

    @Override
    public int getCount() {
        return pairs.size();
    }

    @Override
    public Object getItem(int position) {
        return pairs.get(position);
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
            convertView = (View) inflater.inflate(R.layout.pair_list_item, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView exchange = (TextView) convertView.findViewById(R.id.exchange);

        name.setText(pairs.get(position).getName());
        exchange.setText(pairs.get(position).getExchange());

        return convertView;
    }
}
