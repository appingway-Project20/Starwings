package com.example.admin.starwingsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StockRateActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private static final int STOCK_RATE_LOADER = 100;
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final String API_URL = "http://starwingslearningdestination.com/php/app_api/apiStock.php";

    TextView stockRateTv, emptyView;
    ProgressBar progressBar;

    private static  final String TAG = StockRateActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_rate);

        stockRateTv = (TextView)findViewById(R.id.stock_rate_tv);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        emptyView =   (TextView)findViewById(R.id.empty_view);

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected){

            stockRateQuery();

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
                if(args == null)
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
                    url = url + "?apikey=" + API_KEY;

                    response = HttpRequest.get(url).body();
                    Log.d(TAG,"response: "+response);
                    return response;
                }
                catch (HttpRequest.HttpRequestException e){
                    e.printStackTrace();
                }
                return response;            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        progressBar.setVisibility(View.INVISIBLE);
        String stockRate = parseJsonAndReturnStockRate(data);

        stockRateTv.setText("The stock rate is: "+stockRate);
        stockRateTv.setTextSize(32);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    private void stockRateQuery(){

        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(STOCK_RATE_LOADER, bundle, this);

    }
    private  String parseJsonAndReturnStockRate(String response){

        String stockRate = null;
        try {
            JSONArray root = new JSONArray(response);
            JSONObject stockObject = root.getJSONObject(0);
             stockRate = stockObject.getString("stock");



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stockRate;

    }
}

