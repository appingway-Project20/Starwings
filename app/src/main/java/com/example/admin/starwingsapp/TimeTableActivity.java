package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lalit on 17-07-2017.
 */

public class TimeTableActivity extends AppCompatActivity {
    private static final String API_URL="http://starwingslearningdestination.com/php/app_api/apiTimetable.php?apikey=zxcvbnm123zxdewas";
    int day;
    String link;
    WebView webView;
    Toolbar toolbar;
    TextView titleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleView = (TextView) toolbar.findViewById(R.id.title);
        titleView.setText("TimeTable");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeTableActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        webView= (WebView) findViewById(R.id.web);
        day=getIntent().getIntExtra("day",0);
        try {
            new RetrieveFeedTask().execute();

        } catch (Exception e) {
            Toast.makeText(TimeTableActivity.this, "Error Occured! Please try again.", Toast.LENGTH_SHORT).show();
        }
        webView.setVisibility(WebView.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        String completeUrl="http://starwing.appingway.com/php/web_api/"+link;
        String doc="<iframe src='http://docs.google.com/viewer?url="+completeUrl+"&embedded=true' width='100%' height='100%'  style='border: none;'></iframe>";
        webView.loadData(doc,"text/html", "utf-8");
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {

        }

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
                JSONObject jsonObject = array.getJSONObject(day);
                link = jsonObject.getString("Link");

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
