package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by AKASH on 09-07-2017.
 */

public class ResourcesActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resources,courses;
    Toolbar toolbar;
    TextView title;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_layout);
        init();
        uid=getIntent().getStringExtra("uid");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title= (TextView) toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Courses");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResourcesActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });


        resources.setOnClickListener(this);
        courses.setOnClickListener(this);
    }

    private void init() {
        resources= (TextView) findViewById(R.id.resources);
        courses= (TextView) findViewById(R.id.courses);
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        if(id==R.id.courses){
            Intent intent = new Intent(ResourcesActivity.this,CourseActivity.class);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }
        if(id==R.id.resources){
            Intent intent = new Intent(ResourcesActivity.this,VideoActivity.class);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }
    }
}
