package com.sachinstarwings.learning.destination.adpaters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 29-07-2017.
 */

public class StockNewsBaseAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> newslist,subjectlist;
    private Typeface fonts1,fonts2;

    public StockNewsBaseAdapter(Context context, ArrayList<String> newslist, ArrayList<String> subjectlist) {
        this.context=context;
        this.newslist=newslist;
        this.subjectlist=subjectlist;
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public Object getItem(int position) {
        return newslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        fonts1 =  Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Light.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Regular.ttf");

        StockNewsBaseAdapter.ViewHolder viewHolder = null;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.content_stock_news,null);

            viewHolder = new StockNewsBaseAdapter.ViewHolder();

            viewHolder.news= (TextView)convertView.findViewById(R.id.stocknews);
            viewHolder.subject = (TextView)convertView.findViewById(R.id.subject);

            viewHolder.news.setTypeface(fonts1);
            viewHolder.subject.setTypeface(fonts2);

            convertView.setTag(viewHolder);

        }else {

            viewHolder = (StockNewsBaseAdapter.ViewHolder)convertView.getTag();
        }

        viewHolder.news.setText(newslist.get(position));
        viewHolder.subject.setText(subjectlist.get(position));

        return convertView;
    }
    public class ViewHolder {

        TextView news;
        TextView subject;
    }
}
