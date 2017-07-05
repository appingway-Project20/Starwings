package com.example.admin.starwingsapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Downloads extends AppCompatActivity {
	Toast t;
	Toolbar toolbar;
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downloads);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		title= (TextView) toolbar.findViewById(R.id.title);
		title.setText("Downloads");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		View v = toolbar.findViewById(R.id.dashboard);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Downloads.this,Dashboard.class);
				startActivity(intent);
			}
		});
		Toast t = Toast.makeText(Downloads.this,
				"My downloads", Toast.LENGTH_SHORT);
		t.show();
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
