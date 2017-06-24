package com.example.admin.starwingsapp;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

public class Downloads extends Activity {
	Toast t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downloads);
		final ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255,100,0)));
		bar.setTitle("My Downloads");
		bar.setDisplayUseLogoEnabled(false);
		bar.setDisplayShowHomeEnabled(false);
		Toast t = Toast.makeText(Downloads.this,
				"My downloads", Toast.LENGTH_SHORT);
		t.show();
	}


}
