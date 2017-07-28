package com.example.admin.starwingsapp.adpaters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.starwingsapp.Bean;
import com.example.admin.starwingsapp.NewsActivity;
import com.example.admin.starwingsapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Lalit on 01/04/16.
 */
public class ListBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Bean>beanlist;
    Typeface fonts1,fonts2;

    public ListBaseAdapter(Context context, ArrayList<Bean> beanlist) {

        this.context = context;
        this.beanlist = beanlist;
    }


    @Override
    public int getCount() {
        return beanlist.size();
    }

    @Override
    public Object getItem(int position) {
        return beanlist.get(position);
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

        ViewHolder viewHolder = null;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list,null);

            viewHolder = new ViewHolder();

            viewHolder.newsimage1 = (ImageView)convertView.findViewById(R.id.newsimage1);
            viewHolder.newsimage2 = (ImageView)convertView.findViewById(R.id.newsimage2);
            viewHolder.more = (ImageView)convertView.findViewById(R.id.more);
            viewHolder.newsname = (TextView)convertView.findViewById(R.id.newsname);
            viewHolder.time = (TextView)convertView.findViewById(R.id.time);
            viewHolder.news = (TextView)convertView.findViewById(R.id.news);
            viewHolder.newssub = (TextView)convertView.findViewById(R.id.newssub);
            viewHolder.author = (TextView)convertView.findViewById(R.id.author);

            viewHolder.newsname.setTypeface(fonts1);
            viewHolder.time.setTypeface(fonts1);
            viewHolder.news.setTypeface(fonts2);
            viewHolder.newssub.setTypeface(fonts1);
            viewHolder.author.setTypeface(fonts1);

            convertView.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder)convertView.getTag();
        }

        Bean bean = (Bean)getItem(position);
        viewHolder.newsimage1.setImageResource(R.drawable.newsname1);
        Picasso.with(context).load(bean.getImgurl()).resize(120, 100).into(viewHolder.newsimage2);
        viewHolder.more.setImageResource(bean.getMore());
        viewHolder.newsname.setText(bean.getNewsname());
        viewHolder.time.setText(bean.getTime());
        viewHolder.news.setText(bean.getNews());
        viewHolder.newssub.setText(bean.getNewssub());
        viewHolder.author.setText(bean.getAuthor());

        return convertView;
    }

    public class ViewHolder {

        ImageView newsimage1;
        ImageView newsimage2;
        ImageView  more;
        TextView newsname;
        TextView time;
        TextView news;
        TextView newssub;
        TextView author;
    }

}

