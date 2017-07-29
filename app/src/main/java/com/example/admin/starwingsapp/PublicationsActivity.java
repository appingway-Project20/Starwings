package com.example.admin.starwingsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.PublicationsAdapter;
import com.example.admin.starwingsapp.models.Publications;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PublicationsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    public static final int PUBLICATIONS_LOADER = 100;
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final String API_URL = "http://starwingslearningdestination.com/php/app_api/apiPublications.php";


    private static final String TAG = PublicationsActivity.class.getSimpleName();

    private Publications publications;

    private ArrayList<Publications> publicationsArrayList;

    ImageView bookIv;
    TextView bookTv, emptyView;
    ProgressBar progressBar;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);

        bookIv = (ImageView)findViewById(R.id.book_image);
        bookTv = (TextView)findViewById(R.id.book_name);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        emptyView = (TextView)findViewById(R.id.empty_view);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            publicationsQuery();

        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            emptyView.setText("No Internet Connection!");
        }

    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {

        return new AsyncTaskLoader<String>(this) {

            @Override
            protected void onStartLoading() {
               super.onStartLoading();
                if(args == null){
                    return;
                }
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
                    url = url + "?apikey=" + API_KEY;

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
        progressBar.setVisibility(View.INVISIBLE);
        publications = new Publications();
//        String imageUrl = "http://starwingslearningdestination.com/php/web_api/"+publications.getmImageUrl();
        parseJson(data);

        adapter = new PublicationsAdapter(publicationsArrayList, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
    private void publicationsQuery(){
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(PUBLICATIONS_LOADER, bundle , this);

    }
    private void parseJson(String response){
        publicationsArrayList = new ArrayList<>();
        try {
            JSONArray root = new JSONArray(response);
            for (int i=0; i< root.length(); i++){
                JSONObject jsonObject = root.getJSONObject(i);
                String imageUrl = jsonObject.getString("Link");
                Log.d(TAG,"image url: "+imageUrl);
                String imageName = jsonObject.getString("name");
                Log.d(TAG,"image name: "+imageName);
                publications = new Publications();
                publications.setmImageName(imageName);
                publications.setmImageUrl(imageUrl);
                publicationsArrayList.add(publications);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
