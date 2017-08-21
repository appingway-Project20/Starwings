package com.sachinstarwings.learning.destination;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lalit on 28-07-2017.
 */

public class StockActivity extends AppCompatActivity {
    @BindView(R.id.stock_news_iv)
    ImageView stockNewsIv;
    @BindView(R.id.stock_news)
    CardView stockNews;
    @BindView(R.id.stock_rate_iv)
    ImageView stockRateIv;
    @BindView(R.id.stock_rate)
    CardView stockRate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.stock_news, R.id.stock_rate})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.stock_news:
                intent = new Intent(StockActivity.this, StockNewsActivity.class);
                startActivity(intent);
                break;
            case R.id.stock_rate:
                intent = new Intent(StockActivity.this, StockRateActivity.class);
                startActivity(intent);
                break;
        }
    }
}
