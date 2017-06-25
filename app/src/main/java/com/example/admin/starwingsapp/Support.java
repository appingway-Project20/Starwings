package com.example.admin.starwingsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AbsListView;
import android.widget.TextView;

public class Support extends AppCompatActivity {
	Toolbar toolbar;
	TextView tvChoice;
	CharSequence[] items = { "App", "AVA", "LMS", "Registration", "Others" };
	String selection;
	AlertDialog my_dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support);
		tvChoice=(TextView) findViewById(R.id.tvChoice);
		/*	toolbar= (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		TextView toolbartitle= (TextView) toolbar.findViewById(R.id.title);
		toolbartitle.setText("Support");*/
		
	
	}
	public void select(View v) {

		AlertDialog.Builder builder = new AlertDialog.Builder(Support.this);
		builder.setTitle("Select");
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				selection = (String) items[arg1];
				tvChoice.setText(selection);
				my_dialog.dismiss();
			}
		});
		my_dialog=builder.create();
		my_dialog.show();
	}

	
	
}
