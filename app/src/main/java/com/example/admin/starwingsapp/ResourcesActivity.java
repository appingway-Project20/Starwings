package com.example.admin.starwingsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.admin.starwingsapp.Login.PREFS_NAME;

/**
 * Created by LALIT on 09-07-2017.
 */

public class ResourcesActivity extends AppCompatActivity {
    @BindView(R.id.ivprofile)
    ImageView profile;
    @BindView(R.id.ivcourses)
    ImageView course;
    @BindView(R.id.ivresource)
    ImageView resource;
    @BindView(R.id.ivprevpap)
    ImageView papers;
    Toolbar toolbar;
    TextView title;
    String uid;
    String profileDetails[];
    @BindView(R.id.logout_btn)
    Button logoutBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_layout);
        ButterKnife.bind(this);
        init();
        uid = getIntent().getStringExtra("uid");
        profileDetails = getIntent().getStringArrayExtra("profile details");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);
        title.setText("Resources");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResourcesActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        resource = (ImageView) findViewById(R.id.ivresource);
        course = (ImageView) findViewById(R.id.ivcourses);
        profile = (ImageView) findViewById(R.id.ivprofile);
        papers = (ImageView) findViewById(R.id.ivprevpap);

    }

    @OnClick({R.id.ivprofile, R.id.ivcourses, R.id.ivresource, R.id.ivprevpap, R.id.logout_btn})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ivcourses) {
            Intent intent = new Intent(ResourcesActivity.this, CourseActivity.class);
            intent.putExtra("uid", uid);
            startActivity(intent);
        }
        if (id == R.id.ivresource) {
            Intent intent = new Intent(ResourcesActivity.this, VideosListActivity.class);
            intent.putExtra("uid", uid);
            startActivity(intent);
        }
        if (id == R.id.ivprofile) {
            Intent intent = new Intent(ResourcesActivity.this, ProfileActivity.class);
            intent.putExtra("profile details", profileDetails);
            startActivity(intent);
        }
        if (id == R.id.ivprevpap) {
            Intent intent = new Intent(ResourcesActivity.this, PapersActivity.class);
            startActivity(intent);
        }
        if(id == R.id.logout_btn){
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("logged");
            editor.commit();
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

}
