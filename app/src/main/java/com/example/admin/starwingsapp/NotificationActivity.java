package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.NotificationAdapter;
import com.example.admin.starwingsapp.models.NotificationData;

import java.util.ArrayList;

/**
 * Created by AKASH on 21-06-2017.
 */

public class NotificationActivity extends AppCompatActivity {
    RecyclerView rv;
    Toolbar toolbar;
    ArrayList<NotificationData> arrayList;
    LinearLayoutManager layoutManager;
    TextView titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        init();
        titleView = (TextView)toolbar.findViewById(R.id.title);
        titleView.setText("Notifications");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });
        layoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        arrayList.add(new NotificationData("This is the title","Here comes the notification details:"));
        rv.setAdapter(new NotificationAdapter(arrayList,this));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        arrayList=new ArrayList<>();
        rv= (RecyclerView) findViewById(R.id.notrv);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
    }
}
