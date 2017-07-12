package com.example.admin.starwingsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.List;

/**
 * Created by Lalit on 12-07-2017.
 */

class DailyScheduleAdapter extends RecyclerView.Adapter<DailyScheduleAdapter.MyViewHolder>{
    private Context context;
    private List<String> urlList;
    String doc;

    public DailyScheduleAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_schedule_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(DailyScheduleAdapter.MyViewHolder holder, int position) {
        String url=urlList.get(position);
        String completeUrl="http://starwing.appingway.com/php/web_api/"+url;
        doc="<iframe src='http://docs.google.com/viewer?url="+completeUrl+"&embedded=true' width='100%' height='100%'  style='border: none;'></iframe>";
        holder.webView.loadData(doc,"text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        WebView webView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.webView = (WebView) itemView.findViewById(R.id.web);
            webView.setVisibility(WebView.VISIBLE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        }
    }
}
