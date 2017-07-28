package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.starwingsapp.adpaters.StockNewsBaseAdapter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StockNewsActivity extends AppCompatActivity {
    String news,subject;
    private StockNewsBaseAdapter stocknewsadapter;
    private ArrayList<String> newslist;
    private ArrayList<String> subjectlist;
    ListView listView;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);
        title.setText("Stock News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StockNewsActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        newslist=new ArrayList<>();
        subjectlist=new ArrayList<>();
        listView= (ExpandableHeightListView) findViewById(R.id.stocknewslist);

        new RetrieveFeedTask().execute();
    }
    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... urls) {

            try {
                URL url = new URL("http://starwingslearningdestination.com/php/app_api/apiNews.php?apikey=zxcvbnm123zxdewas");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line="";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {

            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            try {
                JSONArray rootArray=new JSONArray(response);
                for( int i=0; i < rootArray.length() ; i++ ) {
                    JSONObject stockObject=rootArray.getJSONObject(i);
                    news=stockObject.getString("news");
                    subject=stockObject.getString("subject");
                    newslist.add(news);
                    subjectlist.add(subject);
                }
                stocknewsadapter = new StockNewsBaseAdapter(StockNewsActivity.this, newslist,subjectlist){};
                listView.setAdapter(stocknewsadapter);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                Log.i("INFO", e.toString());
            }

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
//        else if (item.getItemId() == R.id.logout){
//            uid = null;
//            Intent intent = new Intent(ResourcesActivity.this, Login.class);
//            startActivity(intent);
//            finish();
//        }

        return super.onOptionsItemSelected(item);
    }

}
