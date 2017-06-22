package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends AppCompatActivity {

    @BindView(R.id.about_us)
    ImageView aboutUs;
    @BindView(R.id.courses)
    ImageView courses;
    @BindView(R.id.schedule)
    ImageView schedule;
    @BindView(R.id.batches)
    ImageView batches;
    @BindView(R.id.editText)
    TextView editText;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        View v = toolbar.findViewById(R.id.dashboard);
        TextView titleView = (TextView)toolbar.findViewById(R.id.title);
        titleView.setText("Dashboard");
        setSupportActionBar(toolbar);
        v.setVisibility(View.INVISIBLE);

    }

    @OnClick({R.id.about_us, R.id.courses, R.id.schedule, R.id.batches, R.id.editText})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.about_us:
                Intent intent = new Intent(Dashboard.this,AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.courses:
                break;
            case R.id.schedule:
                break;
            case R.id.batches:
                break;
            case R.id.editText:
                break;
        }
    }
}
