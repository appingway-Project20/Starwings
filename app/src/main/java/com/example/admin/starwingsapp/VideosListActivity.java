package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.VideosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Lalit on 07-07-2017.
 */

public class VideosListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, VideosAdapter.ListItemClickListener {

    private static final int VIDEO_LOADER = 100;
    private static final String API_URL="http://starwingslearningdestination.com/php/app_api/apiTopic.php";
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final String TAG = VideosListActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private static String videoName;

    private TextView emptyViewTv;

    private String videoPath;

    private ArrayList<String> mVideoUris;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_videoactivity);

        emptyViewTv = (TextView)findViewById(R.id.empty_view);
//        getSupportLoaderManager().initLoader(VIDEO_LOADER,null,this);
        fetchVideosQuery();
    }


    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        Log.d(TAG,"Loader created");
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(args==null)
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
                    url = url + "?apikey=" + API_KEY + "&chapter_id=90789901041997191098" + "&video=1";

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
     String videoPath = parseJsonAndReturnUrl(data);
        if(videoPath!= null){
            File videoFile = new File(videoPath);
        videoName = videoFile.getName();
        Log.d(TAG,"video name: "+videoName);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new VideosAdapter(videoName,this);
        mRecyclerView.setAdapter(mAdapter);
        }
        else{
            emptyViewTv.setText("No videos to display!");
        }

        //videoPath = "http://starwing.appingway.com/php/web_api/uploads/595fb4de67b0b02.AIPursuit.mp4";
//        File videoFile = new File(videoPath);
//        videoName = videoFile.getName();
//        Log.d(TAG,"video name: "+videoName);
//        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAdapter = new VideosAdapter(videoName,this);
//        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
    private void fetchVideosQuery(){
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();
//        Loader<String> loader = loaderManager.getLoader(VIDEO_LOADER);
        loaderManager.initLoader(VIDEO_LOADER,queryBundle,this);
//        if (loader == null) {
//            loaderManager.initLoader(VIDEO_LOADER, queryBundle, this);

    }
    // parses json and extracts url of the video file.
    private String parseJsonAndReturnUrl(String jsonData){
        String videoUri = null;
        try {
            JSONObject root = new JSONObject(jsonData);
            JSONArray topicsArray = root.getJSONArray("topic_data");
            if(topicsArray.length() == 0){
                return  null;
            }
            for(int i=0; i< topicsArray.length(); i++){

            }
            JSONArray firstTopic = topicsArray.getJSONArray(0);
             videoUri = firstTopic.getString(3);
            Log.d(TAG, "video path: "+videoUri);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return videoUri;

    }


    @Override
    public void onListItemClicked(int position) {
        Intent intent = new Intent(VideosListActivity.this, VideoActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, videoPath);
        startActivity(intent);
    }

}

