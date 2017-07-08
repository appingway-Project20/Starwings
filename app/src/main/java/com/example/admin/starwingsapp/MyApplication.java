package com.example.admin.starwingsapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by AKASH on 17-02-2017.
 */

public class MyApplication extends Application {
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication gatInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
