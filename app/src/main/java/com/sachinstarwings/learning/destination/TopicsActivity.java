package com.sachinstarwings.learning.destination;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sachinstarwings.learning.destination.adpaters.TopicsAdapter;
import com.sachinstarwings.learning.destination.models.TopicData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by AKASH on 07-07-2017.
 */

public class TopicsActivity extends AppCompatActivity {
    public JsonObjectRequest request;
    public RequestQueue mRequestQueue;
    RecyclerView trv;
    LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    TextView titlen,title;
    public ArrayList<TopicData> arrayList = new ArrayList<>();
    public String url = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        String topic_id = getIntent().getStringExtra("tid");
        String tname = getIntent().getStringExtra("cname");
        titlen = (TextView) findViewById(R.id.tname);
        titlen.setText(tname);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title= (TextView) toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Topics");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicsActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });

        mRequestQueue = VolleySingleton.getInstance().getmRequestQueue();
        url = "http://starwingslearningdestination.com/php/app_api/apiTopic.php?apikey=zxcvbnm123zxdewas&chapter_id="+topic_id;
        trv = (RecyclerView) findViewById(R.id.trv);
        layoutManager = new LinearLayoutManager(this);
        trv.setLayoutManager(layoutManager);

        loaddata();

    }

    public void loaddata() {

        request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject mainObject) {
                try {
                    Log.d("TAG", "onPostExecute: " + mainObject.toString());
                    String error = mainObject.getString("error");
                    String success = mainObject.getString("result");
                    JSONArray jarray = mainObject.getJSONArray("topic_data");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONArray actor = jarray.getJSONArray(i);
                        String title = actor.getString(0);
                        String date = actor.getString(1);
                        String author = actor.getString(2);
                        String link = actor.getString(3);

                        arrayList.add(new TopicData(title, author, date, link));
                    }
                    trv.setAdapter(new TopicsAdapter(arrayList, TopicsActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(TopicsActivity.this,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TopicsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(request);
    }
}
