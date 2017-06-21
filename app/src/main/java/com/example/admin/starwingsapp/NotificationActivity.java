package com.example.admin.starwingsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by AKASH on 21-06-2017.
 */

public class NotificationActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<NotificationData> arrayList;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        init();
        layoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        arrayList.add(new NotificationData("This is the title","Here comes the notification details:"));
        rv.setAdapter(new NotificationAdapter(arrayList,this));
    }

    private void init() {
        arrayList=new ArrayList<>();
        rv= (RecyclerView) findViewById(R.id.notrv);
    }
}
