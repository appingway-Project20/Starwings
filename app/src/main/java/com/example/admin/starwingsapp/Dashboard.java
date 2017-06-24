package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.Dash;

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
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.dashboard)
    LinearLayout dashboard;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.centers)
    ImageView centers;
    @BindView(R.id.downloads)
    ImageView downloads;
    @BindView(R.id.notifications)
    ImageView notifications;
    @BindView(R.id.lms)
    ImageView lms;
    @BindView(R.id.contact_details)
    ImageView contactDetails;
    @BindView(R.id.support)
    ImageView support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        View v = toolbar.findViewById(R.id.dashboard);
        TextView titleView = (TextView) toolbar.findViewById(R.id.title);
        titleView.setText("Dashboard");
        setSupportActionBar(toolbar);
        v.setVisibility(View.INVISIBLE);

    }

    @OnClick({R.id.about_us, R.id.courses, R.id.schedule, R.id.batches, R.id.editText,R.id.centers, R.id.downloads, R.id.notifications, R.id.lms, R.id.contact_details, R.id.support})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.about_us:
                intent = new Intent(Dashboard.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.courses:
                intent=new Intent(Dashboard.this,OurCourses.class);
                startActivity(intent);
                break;
            case R.id.schedule:
                break;
            case R.id.batches:
                intent = new Intent(Dashboard.this, BatchesActivity.class);
                startActivity(intent);
                break;
            case R.id.editText:
                break;
            case R.id.centers:
                intent=new Intent(Dashboard.this,CenterActivity.class);
                startActivity(intent);
                break;
            case R.id.downloads:
                intent=new Intent(Dashboard.this,Downloads.class);
                startActivity(intent);
                break;
            case R.id.notifications:
                intent=new Intent(Dashboard.this,NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.lms:
                intent=new Intent(Dashboard.this,Login.class);
                startActivity(intent);
                break;
            case R.id.contact_details:
                intent = new Intent(Dashboard.this,ContactInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.support:
                intent=new Intent(Dashboard.this,Support.class);
                startActivity(intent);
                break;
        }
    }


    }

