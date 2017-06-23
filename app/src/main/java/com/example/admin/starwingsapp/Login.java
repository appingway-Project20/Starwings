package com.example.admin.starwingsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255,100,0)));
		bar.setTitle("Login");
		bar.setDisplayUseLogoEnabled(false);
		bar.setDisplayShowHomeEnabled(false);
	}

	
}
