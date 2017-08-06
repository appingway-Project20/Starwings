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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.PapersAdapter;
import com.example.admin.starwingsapp.models.PapersData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PapersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, PapersAdapter.ListItemClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private static final int PAPERS_LOADER = 101;
    private static final String API_URL="http://starwingslearningdestination.com/php/app_api/apiPaper.php";
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final String TAG = VideosListActivity.class.getSimpleName();

    ArrayList<PapersData> papersData;

    ProgressBar progressBar;

    private TextView emptyViewTv;

    String cid, fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);

        setTitle("Papers");
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        emptyViewTv = (TextView)findViewById(R.id.empty_view);
        fetchPapersQuery();

         cid = getIntent().getStringExtra("cid");
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
                    url = url + "?course_id=" + cid + "&apikey=" + API_KEY;

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

//        String fileName = parseJsonAndReturnFileName(data);
//        fileUrl = fileName;
//        String segments[] = fileName.split(".");
//        fileName = segments[segments.length - 2] + ".pdf";
        progressBar.setVisibility(View.INVISIBLE);
        parseJsonAndReturnFileName(data);

        if(papersData!= null){
            mAdapter = new PapersAdapter(papersData,this);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            emptyViewTv.setText("No papers to display!");
        }

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
    private void parseJsonAndReturnFileName(String jsonData){
        papersData = new ArrayList<>();
        PapersData paperData = null;
        fileName = null;
        String year;
        try {
            JSONObject root = new JSONObject(jsonData);

            JSONArray topicsArray = root.getJSONArray("chapter_id:chapter_name:noT");
            if(topicsArray.length() == 0){
                return;
            }
            for(int i = 0; i<topicsArray.length(); i++){
                JSONArray firstTopic = topicsArray.getJSONArray(i);
                fileName = firstTopic.getString(1);
                year = firstTopic.getString(2);

                paperData = new PapersData();

                paperData.setmFilename(fileName);
                paperData.setYear(year);
                papersData.add(paperData);
                Log.d(TAG, "file path: "+fileName);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onListItemClicked(int itemPosition) {
        Intent intent = new Intent(PapersActivity.this, PapersDisplayActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, fileName);
        startActivity(intent);
    }
}
