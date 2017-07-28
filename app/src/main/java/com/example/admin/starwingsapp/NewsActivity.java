package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.admin.starwingsapp.adpaters.ListBaseAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    String author,articleTitle,description,articleurl,urlToImage,publishedAt;
    private ExpandableHeightListView listview;
    private ArrayList<Bean> BeanList;
    private ArrayList<String> urlList;
    private ListBaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listview = (ExpandableHeightListView)findViewById(R.id.listview);

        BeanList = new ArrayList<Bean>();
        urlList=new ArrayList<String>();
        new RetrieveFeedTask().execute();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(NewsActivity.this,NewsDetailActivity.class);
                intent.putExtra("url",urlList.get(position));
                startActivity(intent);
            }
        });
    }
    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {

        }

        protected String doInBackground(Void... urls) {

            try {
                URL url = new URL("https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=3e5bba7d8fc146be9a874489e1effb30");
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
            JSONArray articlesArray;
            JSONObject root,articleObject;

            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            try {
                root=new JSONObject(response);
                articlesArray=root.getJSONArray("articles");
                for( int i=0; i < articlesArray.length() ; i++ ) {
                    articleObject=articlesArray.getJSONObject(i);
                    author=articleObject.getString("author");
                    articleTitle=articleObject.getString("title");
                    description=articleObject.getString("description");
                    articleurl=articleObject.getString("url");
                    urlToImage=articleObject.getString("urlToImage");
                    publishedAt=articleObject.getString("publishedAt");

                    urlList.add(articleurl);

                    Bean bean = new Bean(R.drawable.newsname1, urlToImage, R.drawable.more,
                            "Business Insider .", publishedAt, articleTitle, description, articleurl, "-"+author);
                    BeanList.add(bean);
                }
                baseAdapter = new ListBaseAdapter(NewsActivity.this, BeanList){};
                listview.setAdapter(baseAdapter);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                Log.i("INFO", e.toString());
            }

        }
    }

}
