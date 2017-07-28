package com.example.admin.starwingsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends AppCompatActivity {

    String filename ="myFile" ;
    @BindView(R.id.home)
    CardView home;
    @BindView(R.id.course)
    CardView courses;
    @BindView(R.id.daily)
    CardView schedule;
    @BindView(R.id.bat)
    CardView batches;
    @BindView(R.id.editText)
    TextView editText;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.dashboard)
    LinearLayout dashboard;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.india)
    CardView centers;
    @BindView(R.id.down)
    CardView downloads;
    @BindView(R.id.not)
    CardView notifications;
    @BindView(R.id.lm)
    CardView lms;
    @BindView(R.id.det)
    CardView contactDetails;
    @BindView(R.id.suppo)
    CardView support;
    @BindView(R.id.news)
    CardView news;
    @BindView(R.id.gallery_container)
    CardView gallery;
    @BindView(R.id.publication)
    CardView publication;
    @BindView(R.id.stocks)
    CardView stocks;

    AlertDialog.Builder builder;
    AlertDialog my_dialog;
    Intent intent;
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
        if(!haveNetworkConnection()){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }

            builder.setTitle("No Internet Connection");
            builder.setMessage("You don't have an internet connection. You won't be able to use LMS!");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            my_dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            my_dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert);
                    my_dialog=builder.create();
                    my_dialog.show();
        }
        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        if(sharedPrefs.getBoolean("firstTime",true)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }

            builder.setTitle("Enable Notifications");
            builder.setMessage("It's good to stay updated and informed! Do you want to enable notifications?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    my_dialog.dismiss();
                    intent =new Intent(Dashboard.this,NotificationActivity.class);

                    startActivity(intent);

                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            my_dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert);
            my_dialog=builder.create();
            my_dialog.show();
            editor.putBoolean("firstTime",false);
            editor.commit();
        }
    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    @OnClick({R.id.home, R.id.course, R.id.daily, R.id.bat, R.id.editText, R.id.india, R.id.down, R.id.not, R.id.lm, R.id.det, R.id.suppo, R.id.news,R.id.gallery_container,R.id.getintouch, R.id.publication, R.id.stocks})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.home:
                intent = new Intent(Dashboard.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.course:
                intent = new Intent(Dashboard.this, OurCourses.class);
                startActivity(intent);
                break;
            case R.id.daily:
                intent=new Intent(Dashboard.this,DailyScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.bat:
                intent = new Intent(Dashboard.this, BatchesActivity.class);
                startActivity(intent);
                break;
            case R.id.editText:
                break;
            case R.id.india:
                intent = new Intent(Dashboard.this, CenterActivity.class);
                startActivity(intent);
                break;
            case R.id.down:
                intent = new Intent(Dashboard.this, Downloads.class);
                startActivity(intent);
                break;
            case R.id.not:
                intent = new Intent(Dashboard.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.lm:
                intent = new Intent(Dashboard.this, Login.class);
                startActivity(intent);
                break;
            case R.id.det:
                intent = new Intent(Dashboard.this, ContactInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.suppo:
                intent = new Intent(Dashboard.this, Support.class);
                startActivity(intent);
                break;
            case R.id.news:
                intent = new Intent(Dashboard.this, NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.gallery_container:
                intent = new Intent(Dashboard.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.getintouch:
                intent = new Intent(Dashboard.this, GetInTouchActivity.class);
                startActivity(intent);
                break;
            case R.id.publication:
                intent = new Intent(Dashboard.this, PublicationsActivity.class);
                startActivity(intent);
                break;
            case R.id.stocks:
                intent = new Intent(Dashboard.this, StockActivity.class);
                startActivity(intent);
                break;
        }
    }



}

