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

public class DailyScheduleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DailyScheduleAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    private TextView titleView;
    private WebView webView;
    private ArrayList<String> urllist;
    private static final String API_URL="http://starwingslearningdestination.com/php/app_api/apiTimetable.php?apikey=zxcvbnm123zxdewas";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_schedule);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webView= (WebView) findViewById(R.id.web);
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
        urllist=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.dailyrecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        try {
            new RetrieveFeedTask().execute();
        }catch (Exception ex){
            Toast.makeText(DailyScheduleActivity.this,ex.toString(),Toast.LENGTH_SHORT);
        }
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {

            if (response == null) {
                response = "THERE WAS AN ERROR";
            }

            Log.i("INFO", response);
            try {

                JSONArray array = new JSONArray(response);
                for(int i=0;i<array.length();i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    String link = jsonObject.getString("Link");
                    urllist.add(link);
                }
                adapter=new DailyScheduleAdapter(DailyScheduleActivity.this,urllist);
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
