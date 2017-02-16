package com.pablogamito.cryptotradinghub;

public class Pair {
    String name;
    String exchange;
    float rate;
    float change;

    public Pair(String name, String exchange) {
        this.name = name;
        this.exchange = exchange;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getChange() {
        return change;
    }
    public void setChange(float change) {
        this.change = change;
    }
}
