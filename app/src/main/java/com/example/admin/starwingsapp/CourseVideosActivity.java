package com.example.admin.starwingsapp;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.CourseChapterAdapter;
import com.example.admin.starwingsapp.models.CourseChapterData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseVideosActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.dashboard)
    LinearLayout dashboard;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;

    private static final int COURSES_LOADER = 101;
    private static final String API_URL="http://starwingslearningdestination.com/php/app_api/apiCourse.php";
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";

    public static final String TAG = CourseVideosActivity.class.getSimpleName();

    private String uid, url;

    public ArrayList<CourseChapterData> arrayList = new ArrayList<>();

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursesapi);
        ButterKnife.bind(this);


        uid=getIntent().getStringExtra("uid");
        url = "http://starwingslearningdestination.com/php/app_api/apiCourse.php?token="+uid+"&apikey=" + API_KEY;

        coursesQuery();
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(args == null)
                    return;
                forceLoad();
            }

            @Override
            public String loadInBackground() {
                String response = HttpRequest.get(url).body();
                Log.d(TAG, "response: "+response);
                parseJSON(response);


                return response;            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CourseChapterAdapter(arrayList, CourseVideosActivity.this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
    private void coursesQuery(){
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(COURSES_LOADER, bundle, this);

    }
    private void parseJSON(String response)  {
        try {
            JSONObject root = new JSONObject(response);
            JSONArray jarray = root.getJSONArray("course_id:course_name:noc");
            if(jarray!= null){
                for (int i = 0; i < jarray.length(); i++) {
                    JSONArray actor = jarray.getJSONArray(i);
                    String course_id = actor.getString(0);
                    String course_name = actor.getString(1);
                    String noc = actor.getString(2);
                    arrayList.add(new CourseChapterData(course_id, course_name, noc));
                }
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
