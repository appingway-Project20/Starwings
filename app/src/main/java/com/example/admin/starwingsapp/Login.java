package com.example.admin.starwingsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


public class Login extends AppCompatActivity {
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		toolbar= (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		TextView toolbartitle= (TextView) toolbar.findViewById(R.id.title);
		toolbartitle.setText("Login");
		
		/*final ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255,100,0)));
		bar.setTitle("Login");
		bar.setDisplayUseLogoEnabled(false);
		bar.setDisplayShowHomeEnabled(false);*/
	}

	
}
