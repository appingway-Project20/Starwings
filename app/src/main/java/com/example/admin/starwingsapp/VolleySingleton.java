package com.example.admin.starwingsapp;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by AKASH on 17-02-2017.
 */

public class VolleySingleton {
    private static VolleySingleton mInstance=null;
    private RequestQueue mRequestQueue;
    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(MyApplication.getAppContext());

    }

    public static VolleySingleton getInstance(){
        if(mInstance==null){
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }
    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }
}
