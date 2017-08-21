package com.sachinstarwings.learning.destination;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sachinstarwings.learning.destination.adpaters.CourseChapterAdapter;
import com.sachinstarwings.learning.destination.models.CourseChapterData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 */

public class CourseActivity extends AppCompatActivity {
    public JsonObjectRequest request;
    public RequestQueue mRequestQueue;
    RecyclerView rv;
    TextView title;
    String uid;
    LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    public ArrayList<CourseChapterData> arrayList = new ArrayList<>();
    public String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursesapi);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title= (TextView) toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        title.setText("Notes");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });
//15495086100953416101
        uid=getIntent().getStringExtra("uid");
        url = "http://starwingslearningdestination.com/php/app_api/apiCourse.php?token="+uid+"&apikey=zxcvbnm123zxdewas";

        mRequestQueue = VolleySingleton.getInstance().getmRequestQueue();
        rv = (RecyclerView) findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
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
                    JSONArray jarray = mainObject.getJSONArray("course_id:course_name:noc");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONArray actor = jarray.getJSONArray(i);
                        String course_id = actor.getString(0);
                        String course_name = actor.getString(1);
                        String noc = actor.getString(2);
                        arrayList.add(new CourseChapterData(course_id, course_name, noc));
                    }
                    rv.setAdapter(new CourseChapterAdapter(arrayList, CourseActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(CourseActivity.this,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CourseActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(request);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }


}
