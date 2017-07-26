package com.example.admin.starwingsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetInTouchActivity extends AppCompatActivity {

    public static final String FACEBOOK_URL = "https://www.facebook.com/Starwings-learning-destination-192452677112041/";
    public static final String INSTAGRAM_URL = "https://www.instagram.com/starwingslearningdestination/";
    public static final String TWITTER_URL = "https://mobile.twitter.com/account";
    public static final String GOOGLE_PLUS_URL = "plus.google.com/u/3/apps/activities";
    @BindView(R.id.facebook_container)
    LinearLayout facebookContainer;
    @BindView(R.id.instagram_container)
    LinearLayout instagramContainer;
    @BindView(R.id.twitter_container)
    LinearLayout twitterContainer;
//    @BindView(R.id.google_plus_container)
//    LinearLayout googlePlusContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_in_touch);
        ButterKnife.bind(this);



    }

    @OnClick({R.id.facebook_container, R.id.instagram_container, R.id.twitter_container})
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
//            case R.id.google_plus_container:
//                intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(GOOGLE_PLUS_URL));
//                startActivity(intent);
//
//                break;
        }
    }
}
