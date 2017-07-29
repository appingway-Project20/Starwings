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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.StockRateAdapter;
import com.example.admin.starwingsapp.models.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StockRateActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Stock>>{

    private static final int STOCK_RATE_LOADER = 100;
    private static final String API_KEY = "zxcvbnm123zxdewas";
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final String API_URL = "http://starwingslearningdestination.com/php/app_api/apiStock.php";

    TextView stockRateTv, emptyView;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<Stock> stocks;

    Stock stock;

    private static  final String TAG = StockRateActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_rate);

        stockRateTv = (TextView)findViewById(R.id.stock_rate_tv);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        emptyView =   (TextView)findViewById(R.id.empty_view);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
    public Loader<ArrayList<Stock>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<ArrayList<Stock>>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(args == null)
                    return;
                forceLoad();
            }

            @Override
            public ArrayList<Stock> loadInBackground() {

                String response = null;
                String url = args.getString(SEARCH_QUERY_URL_EXTRA);
                Log.i(TAG,"url: "+url);
                if(url == null || TextUtils.isEmpty(url)){
                    return null;
                }try {
                    url = url + "?apikey=" + API_KEY;

                    response = HttpRequest.get(url).body();
                    Log.d(TAG,"response: "+response);

                    stocks = parseJsonAndReturnStockRate(response);
                    return stocks;
                }
                catch (HttpRequest.HttpRequestException e){
                    e.printStackTrace();
                }
                return stocks;            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Stock>> loader, ArrayList<Stock> data) {

        progressBar.setVisibility(View.INVISIBLE);
        adapter = new StockRateAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Stock>> loader) {

    }


    private void stockRateQuery(){

        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(STOCK_RATE_LOADER, bundle, this);

    }
    private  ArrayList<Stock> parseJsonAndReturnStockRate(String response){
        stocks = new ArrayList<>();

        String stockRate = null;
        String companyName = null;
        try {
            JSONArray root = new JSONArray(response);
            for (int i=0; i<root.length(); i++){
                JSONObject stockObject = root.getJSONObject(i);
                stockRate = stockObject.getString("stock");
                companyName = stockObject.getString("company");
                stock = new Stock();

                stock.setmCompanyName(companyName);
                stock.setmStockRate(stockRate);

                stocks.add(stock);

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stocks;

    }
}

