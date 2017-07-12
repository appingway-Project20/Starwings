package com.example.admin.starwingsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lalit on 10-07-2017.
 */

public class ProfileActivity extends AppCompatActivity{
    private Toolbar toolbar;
    String profileDetails[];
    private TextView name,dob,phone,title;
    private FrameLayout frame;
    private ImageView image;
    Bitmap bmp;
    final static String API_URL="http://starwing.appingway.com/php/web_api/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);
        init();
        title.setText("My Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });
        profileDetails=getIntent().getStringArrayExtra("profile details");
        name.setText(profileDetails[0]);
        dob.setText(profileDetails[2]);
        phone.setText(profileDetails[3]);
        new GetProfileDetails().execute();
    }

    public void init() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        title= (TextView) toolbar.findViewById(R.id.title);
        name= (TextView) findViewById(R.id.name);
        phone= (TextView) findViewById(R.id.phone);
        dob= (TextView) findViewById(R.id.dob);
        frame= (FrameLayout) findViewById(R.id.frame);
        image= (ImageView) findViewById(R.id.profilepic);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetProfileDetails extends AsyncTask<Void,Void,Bitmap> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            try {
                URL url = new URL(API_URL + profileDetails[1]);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                bmp= BitmapFactory.decodeStream(conn.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmp;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Drawable dr=new BitmapDrawable(bmp);
            frame.setBackgroundDrawable(dr);
        }

    }
}
