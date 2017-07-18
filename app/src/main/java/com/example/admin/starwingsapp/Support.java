package com.example.admin.starwingsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Support extends AppCompatActivity {
	Toolbar toolbar;
	TextView tvChoice;
	CharSequence[] items = { "App", "AVA", "LMS", "Registration", "Others" };
	String selection;
	EditText name,email,mobile,query;
	String stname,stemail,stmobile,stquery,stchoice;
	AlertDialog my_dialog;
	Button submit;
	private TextView choice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support);
		init();
		TextView toolbartitle= (TextView) toolbar.findViewById(R.id.title);
		toolbartitle.setText("Support");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		View v = toolbar.findViewById(R.id.dashboard);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Support.this,Dashboard.class);
				startActivity(intent);
				finish();
			}
		});
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stname=name.getText().toString();
				stmobile=mobile.getText().toString();
				stemail=email.getText().toString();
				stquery=query.getText().toString();
				stchoice=choice.getText().toString();
				if(stname.equals("") || stmobile.equals("")||stemail.equals("")||stquery.equals("")||stchoice.equals("Select")) {
					Toast.makeText(Support.this, "Please fill all the entries!", Toast.LENGTH_SHORT);
				}else {
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(Intent.EXTRA_EMAIL, new String[]{"starwingslearningdestination.com"});
					i.putExtra(Intent.EXTRA_SUBJECT, choice.getText().toString());
					i.putExtra(Intent.EXTRA_TEXT, stquery + "\n" + "Regards,\n" + stname + ",\n" + stmobile);
					try {
						startActivity(Intent.createChooser(i, "Send mail..."));
					} catch (android.content.ActivityNotFoundException ex) {
						Toast.makeText(Support.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	
	}

	private void init() {
		tvChoice=(TextView) findViewById(R.id.tvChoice);
		toolbar= (Toolbar) findViewById(R.id.toolbar);
		submit= (Button) findViewById(R.id.btSubmit);
		name= (EditText) findViewById(R.id.etName);
		mobile= (EditText) findViewById(R.id.etmobile);
		email= (EditText) findViewById(R.id.etEmail);
		query= (EditText) findViewById(R.id.etQuery);
		choice=(TextView) findViewById(R.id.tvChoice);
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
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// handle arrow click here
		if (item.getItemId() == android.R.id.home) {
			finish(); // close this activity and return to preview activity (if there is any)
		}

		return super.onOptionsItemSelected(item);
	}

}
