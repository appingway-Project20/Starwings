package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.CourseChapterAdapter;
import com.example.admin.starwingsapp.models.CourseChapterData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VideosChapterActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    RecyclerView rv;
    TextView title;
    private Toolbar toolbar;

    LinearLayoutManager layoutManager;
    public ArrayList<CourseChapterData> arrayList = new ArrayList<>();
    public String url = "";

    private static final int CHAPTERS_LOADER = 101;
    private static final String API_URL="http://starwingslearningdestination.com/php/app_api/apiCourse.php";
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";

    public static final String TAG = VideosChapterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title= (TextView) toolbar.findViewById(R.id.title);
        rv = (RecyclerView)findViewById(R.id.rv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Notes");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideosChapterActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });
        String cid = getIntent().getStringExtra("cid");
        url = "http://starwingslearningdestination.com/php/app_api/apiChapter.php?course_id="+cid+"&apikey=zxcvbnm123zxdewas";

        chaptersQuery();
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
                return response;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CourseChapterAdapter(arrayList, VideosChapterActivity.this));

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
    private void chaptersQuery(){
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(CHAPTERS_LOADER, bundle, this);

    }

    private void parseJSON(String response){
        JSONObject root = null;
        try {
            root = new JSONObject(response);
            JSONArray jarray = root.getJSONArray("chapter_id:chapter_name:noT");
            for (int i = 0; i < jarray.length(); i++) {
                JSONArray actor = jarray.getJSONArray(i);
                String chapter_id = actor.getString(0);
                String chapter_name = actor.getString(1);
                String noc = actor.getString(2);

                arrayList.add(new CourseChapterData(chapter_id, chapter_name, noc));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
