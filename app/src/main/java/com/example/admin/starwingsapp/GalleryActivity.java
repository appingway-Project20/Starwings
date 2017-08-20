package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.GalleryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<String>>, GalleryAdapter.OnListItemClickListener {

    private static final String TAG = GalleryActivity.class.getSimpleName();
    private static final String API_URL = "http://starwingslearningdestination.com/php/web_api/apiGallery.php";
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    TextView title;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdpater;

    private Toolbar toolbar;

    private ArrayList<String> imageUrls;


    private static final int IMAGES_LOADER = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);
        title.setText("Gallery");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        });


        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        fetchImageUrlsQuery();
    }

    private void fetchImageUrlsQuery(){
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(IMAGES_LOADER, bundle, this);
    }

    @Override
    public Loader<ArrayList<String>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<ArrayList<String>>(this) {
            @Override
            protected void onStartLoading() {
                if(args == null)
                    return;
                forceLoad();
                super.onStartLoading();
            }

            @Override
            public ArrayList<String> loadInBackground() {
                String response = null;
                ArrayList<String> links = new ArrayList<>();

                String url = args.getString(SEARCH_QUERY_URL_EXTRA);
                Log.i(TAG,"url: "+url);
                if(url == null || TextUtils.isEmpty(url)){
                    return null;
                }try {
                   // url = url + "?apikey=" + API_KEY;

                    response = HttpRequest.get(url).body();
                    Log.d(TAG,"response: "+response);
                    links = parseJsonAndReturnImageUrls(response);
                }
                catch (HttpRequest.HttpRequestException e){
                    e.printStackTrace();
                }
                return links;
            }
        };
    }

    private ArrayList<String> parseJsonAndReturnImageUrls(String jsonData){
        String imageUri = null;

        imageUrls = new ArrayList<>();
        try {
            JSONArray root = new JSONArray(jsonData);
            for (int i=0; i< root.length(); i++){
                JSONObject imageLinksProperty = root.getJSONObject(i);
                imageUri = imageLinksProperty.getString("Link").trim();
                Log.d(TAG,"Link: "+imageUri);
                imageUrls.add(imageUri);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageUrls;

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
        mAdpater = new GalleryAdapter(data,this, this);
        mRecyclerView.setAdapter(mAdpater);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<String>> loader) {

    }

    @Override
    public void onListItemClicked(int position) {
        Intent intent = new Intent(GalleryActivity.this, DisplayGalleryPictureActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, imageUrls.get(position));
        startActivity(intent);
    }
}
