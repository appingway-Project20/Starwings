package com.example.admin.starwingsapp.models;

/**
 * Created by admin on 7/30/2017.
 */

public class Stock {

    private String mCompanyName;
    private String mStockRate;

    public Stock() {
    }

    public void setmCompanyName(String mCompanyName) {
        this.mCompanyName = mCompanyName;
    }

    public void setmStockRate(String mStockRate) {
        this.mStockRate = mStockRate;
    }

    public String getmCompanyName() {
        return mCompanyName;
    }

    public String getmStockRate() {
        return mStockRate;
    }
}
