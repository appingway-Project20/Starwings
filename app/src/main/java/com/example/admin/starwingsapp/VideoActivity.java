package com.example.admin.starwingsapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener {
    private static final String TAG = VideoActivity.class.getSimpleName();

    private static final String API_URL = "http://starwingslearningdestination.com/php/web_api/";
    String videoPath;

    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
            videoPath = intent.getStringExtra(Intent.EXTRA_TEXT);
            videoPath = API_URL + videoPath;
            videoPath = videoPath.replaceAll("\\s+","%20");
            Log.d(TAG,"path: "+ videoPath);
            videoView = (VideoView)findViewById(R.id.videoView);

            videoView.setOnCompletionListener(this);
            videoView.setOnPreparedListener(this);
//            videoView.setOnTouchListener(this);

            videoView.setVideoPath(videoPath);

//            DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
//            android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
//            params.width =  metrics.widthPixels;
//            params.height = metrics.heightPixels;
//            params.leftMargin = 0;
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

//            videoView.setLayoutParams(params);
            videoView.start();
        }

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        finish();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.setLooping(true);

    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        stopPlaying();
//        return true;
//    }
    public void stopPlaying() {
        videoView.stopPlayback();
        this.finish();
    }

}
