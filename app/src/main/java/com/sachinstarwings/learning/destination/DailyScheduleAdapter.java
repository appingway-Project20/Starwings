package com.sachinstarwings.learning.destination;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

/**
 * Created by Lalit on 12-07-2017.
 */

class DailyScheduleAdapter extends RecyclerView.Adapter<DailyScheduleAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<String> urllist;

    public DailyScheduleAdapter(Context contetxt,ArrayList<String> list) {
        this.context = context;
        this.urllist = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_schedule_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyScheduleAdapter.MyViewHolder holder, int position) {
        //String url=urlList.get(position);
        holder.webView.setVisibility(WebView.VISIBLE);
        String completeUrl="http://starwingslearningdestination.com/php/web_api/"+urllist.get(position);
        String doc="http://docs.google.com/viewer?url="+completeUrl;
        //holder.webView.loadData(doc,"text/html", "utf-8");
        holder.webView.loadUrl(doc);
        holder.webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public int getItemCount() {
        return urllist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        WebView webView;

        public MyViewHolder(View itemView) {
            super(itemView);
            webView= (WebView) itemView.findViewById(R.id.web);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        }
    }
}