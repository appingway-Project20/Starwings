package com.example.admin.starwingsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Lalit on 28-07-2017.
 */

public class NewsDetailActivity extends AppCompatActivity{
    WebView newsWeb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        newsWeb= (WebView) findViewById(R.id.newsweb);
        newsWeb.loadUrl(getIntent().getStringExtra("url"));
        newsWeb.setWebViewClient(new WebViewClient());
    }
}
