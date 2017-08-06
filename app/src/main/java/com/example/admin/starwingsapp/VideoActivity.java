package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    private static final String TAG = VideoActivity.class.getSimpleName();

    private static final String API_URL = "http://starwingslearningdestination.com/php/web_api/";
    String videoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
            videoPath = intent.getStringExtra(Intent.EXTRA_TEXT);
            videoPath = API_URL + videoPath;
            Log.d(TAG,"path: "+ videoPath);
            VideoView videoView = (VideoView)findViewById(R.id.videoView);
            videoView.setVideoPath(videoPath);

            DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
            android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
            params.width =  metrics.widthPixels;
            params.height = metrics.heightPixels;
            params.leftMargin = 0;
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            videoView.setLayoutParams(params);
            videoView.start();
        }

    }
}
