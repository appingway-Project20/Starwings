package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.example.admin.starwingsapp.adpaters.PapersAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PapersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, PapersAdapter.ListItemClickListener{
    ArrayList<String> fileNames;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private static final int PAPERS_LOADER = 101;
    private static final String API_URL="http://starwing.appingway.com/php/app_api/apiPaper.php";
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final String TAG = VideosListActivity.class.getSimpleName();
    String fileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchPapersQuery();
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (args == null)
                    return;
                forceLoad();
            }

            @Override
            public String loadInBackground() {
                String response = null;
                String url = args.getString(SEARCH_QUERY_URL_EXTRA);
                Log.i(TAG,"url: "+url);
                if(url == null || TextUtils.isEmpty(url)){
                    return null;
                }try {
                    url = url + "?course_id=2147483647" + "&apikey=" + API_KEY;

                    response = HttpRequest.get(url).body();
                    Log.d(TAG,"response: "+response);
                    return response;
                }
                catch (HttpRequest.HttpRequestException e){
                    e.printStackTrace();
                }
                return response;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        String fileName = parseJsonAndReturnFileName(data);
        fileUrl = fileName;
//        String segments[] = fileName.split(".");
//        fileName = segments[segments.length - 2] + ".pdf";
        fileNames = new ArrayList<>();
        fileNames.add(fileName);
        mAdapter = new PapersAdapter(fileNames,this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
    //call this method to start the loader
    private void fetchPapersQuery(){
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(PAPERS_LOADER, bundle, this);
    }
    private String parseJsonAndReturnFileName(String jsonData){
        String fileName = null;
        try {
            JSONObject root = new JSONObject(jsonData);
            JSONArray topicsArray = root.getJSONArray("chapter_id:chapter_name:noT");
            JSONArray firstTopic = topicsArray.getJSONArray(0);
            fileName = firstTopic.getString(1);
            Log.d(TAG, "video path: "+fileName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fileName;

    }

    @Override
    public void onListItemClicked(int itemPosition) {
        Intent intent = new Intent(PapersActivity.this, PapersDisplayActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT,fileUrl);
        startActivity(intent);
    }
}
