package com.example.admin.starwingsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.AbsListView;

public class Support extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support);
		final ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255,100,0)));
		bar.setTitle("Support");
		bar.setDisplayUseLogoEnabled(false);
		bar.setDisplayShowHomeEnabled(false);
		
	
	}

	
	
}
