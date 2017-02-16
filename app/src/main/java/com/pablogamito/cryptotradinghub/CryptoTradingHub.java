package com.pablogamito.cryptotradinghub;

import android.app.Application;

import java.util.ArrayList;

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

}
