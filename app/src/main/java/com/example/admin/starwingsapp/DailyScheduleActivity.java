package com.example.admin.starwingsapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DailyScheduleActivity extends AppCompatActivity implements DailyScheduleAdapter.ListItemClickListener{
    private RecyclerView recyclerView;
    private DailyScheduleAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    private TextView titleView;
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_schedule);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleView = (TextView) toolbar.findViewById(R.id.title);
        titleView.setText("Daily Schedule");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DailyScheduleActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.dailyrecycler);
        adapter = new DailyScheduleAdapter(this,days,this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAlpha(0);
        recyclerView.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // This will give me the initial first and last visible element's position.
                // This is required as only this elements needs to be animated
                // Start will be always zero in this case as we are calling in onCreate
                int start = layoutManager.findFirstVisibleItemPosition();
                int end = layoutManager.findLastVisibleItemPosition();

                Log.i("Start: ", start + "");
                Log.i("End: ", end + "");

                // Multiplication factor
                int DELAY = 700;

                // Loop through all visible element
                for (int i = start; i <= end; i++) {
                    Log.i("Animatining: ", i + "");

                    // Get View
                    View v = recyclerView.findViewHolderForAdapterPosition(i).itemView;

                    // Hide that view initially
                    v.setAlpha(0);
                    // Setting animations: slide and alpha 0 to 1
                    PropertyValuesHolder slide = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 150, 0);
                    PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0, 1);
                    ObjectAnimator a = ObjectAnimator.ofPropertyValuesHolder(v, slide, alpha);
                    a.setDuration(300);

                    // It will set delay. As loop progress it will increment
                    // And it will look like items are appearing one by one.
                    // Not all at a time
                    a.setStartDelay(i * DELAY);

                    a.setInterpolator(new DecelerateInterpolator());

                    a.start();

                }

                // Set Recycler View visible as all visible are now hidden
                // Animation will start, so set it visible
                recyclerView.setAlpha(1);

            }
        }, 50);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onListItemClicked(int position) {
        Intent intent= new Intent(DailyScheduleActivity.this,TimeTableActivity.class);
        intent.putExtra("day",position);
        startActivity(intent);
    }
}
