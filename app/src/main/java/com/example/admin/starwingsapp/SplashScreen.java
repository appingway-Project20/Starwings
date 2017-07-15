package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//import com.google.android.gms.maps.model.Dash;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME = 2000;
    static int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread th= new Thread(){
            @Override
            public void run() {
                try {
                    sleep(SPLASH_TIME);

                    Intent intent= new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }
}
