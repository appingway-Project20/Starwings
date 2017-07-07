package com.example.admin.starwingsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.admin.starwingsapp.adpaters.CourseChapterAdapter;
import com.example.admin.starwingsapp.models.CourseChapterData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by AKASH on 07-07-2017.
 */

public class ChapterActivity extends AppCompatActivity {
    public JsonObjectRequest request;
    public RequestQueue mRequestQueue;
    RecyclerView rv;
    private Toolbar toolbar;

    LinearLayoutManager layoutManager;
    public ArrayList<CourseChapterData> arrayList = new ArrayList<>();
    public String url = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Courses");
        String cid = getIntent().getStringExtra("cid");
        url = "https://techinsta22.000webhostapp.com/app_api/apiChapter.php?course_id=669774461904187&apikey=zxcvbnm123zxdewas";
        mRequestQueue = VolleySingleton.getInstance().getmRequestQueue();
        rv = (RecyclerView) findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        loaddata();
    }

    public void loaddata() {
        arrayList.add(new CourseChapterData("1", "hello", "chapter"));
        request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject mainObject) {
                try {
                    Log.d("TAG", "onPostExecute: " + mainObject.toString());
                    String error = mainObject.getString("error");
                    String success = mainObject.getString("result");
                    JSONArray jarray = mainObject.getJSONArray("chapter_id:chapter_name:noT");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONArray actor = jarray.getJSONArray(i);
                        String chapter_id = actor.getString(0);
                        String chapter_name = actor.getString(1);
                        String noc = actor.getString(2);

                        arrayList.add(new CourseChapterData(chapter_id, chapter_name, noc));
                        Toast.makeText(ChapterActivity.this, jarray.toString() + "    " + chapter_id + " " + chapter_name + " " + noc, Toast.LENGTH_SHORT).show();
                    }
                    rv.setAdapter(new CourseChapterAdapter(arrayList, ChapterActivity.this));
                    Toast.makeText(ChapterActivity.this, arrayList.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ChapterActivity.this,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChapterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(request);
    }
}
