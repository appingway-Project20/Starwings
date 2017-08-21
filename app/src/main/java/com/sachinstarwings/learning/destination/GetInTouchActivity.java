package com.sachinstarwings.learning.destination;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetInTouchActivity extends AppCompatActivity {

    public static final String FACEBOOK_URL = "https://www.facebook.com/Starwings-learning-destination-192452677112041/";
    public static final String INSTAGRAM_URL = "https://www.instagram.com/starwingslearningdestination/";
    public static final String TWITTER_URL = "https://mobile.twitter.com/StarwingsLearn";
    public static final String GOOGLE_PLUS_URL = "https://plus.google.com/u/3/apps/activities";
    public static final String YOUTUBE_URL = "https://www.youtube.com/channel/UCh93XBdx8nhNQxkxnmAPRIw";
    Toolbar toolbar;
    TextView title;
    @BindView(R.id.facebook_container)
    LinearLayout facebookContainer;
    @BindView(R.id.instagram_container)
    LinearLayout instagramContainer;
    @BindView(R.id.twitter_container)
    LinearLayout twitterContainer;
    @BindView(R.id.google_plus_container)
    LinearLayout googlepluscontainer;
    @BindView(R.id.youtube_container)
    LinearLayout youtubeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_in_touch);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);
        title.setText("Get In Touch");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetInTouchActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });


    }

    @OnClick({R.id.facebook_container, R.id.instagram_container, R.id.twitter_container, R.id.google_plus_container,R.id.youtube_container})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.facebook_container:
                 intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(FACEBOOK_URL));
                startActivity(intent);
                break;
            case R.id.instagram_container:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(INSTAGRAM_URL));
                startActivity(intent);

                break;

            case R.id.twitter_container:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(TWITTER_URL));
                startActivity(intent);

                break;
            case R.id.google_plus_container:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(GOOGLE_PLUS_URL));
                startActivity(intent);

                break;
            case R.id.youtube_container:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(YOUTUBE_URL));
                startActivity(intent);

                break;
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
