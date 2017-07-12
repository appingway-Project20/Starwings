package com.example.admin.starwingsapp;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Login extends AppCompatActivity {
	Toolbar toolbar;
	EditText etname,etregisno;
	Button btlogin;
	ProgressBar progressBar;
	String name,reg_number;
	TextView responseView;
	private static final String API_URL="http://starwing.appingway.com/php/app_api/apiLogin.php?";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		bridge();
		progressBar.setVisibility(View.INVISIBLE);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		TextView toolbartitle= (TextView) toolbar.findViewById(R.id.title);
		toolbartitle.setText("Login");
		View v = toolbar.findViewById(R.id.dashboard);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Login.this,Dashboard.class);
				startActivity(intent);
			}
		});

		btlogin.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				name=etname.getText().toString();
				reg_number=etregisno.getText().toString();
				if(name.equals("")){
					Toast.makeText(Login.this,"What's your good name?",Toast.LENGTH_SHORT).show();
				}else if(reg_number.equals("")){
					Toast.makeText(Login.this,"You do have a registration number,don't you?",Toast.LENGTH_SHORT).show();
				}
				else {
					try {
						new RetrieveFeedTask().execute();

					} catch (Exception e) {
						Toast.makeText(Login.this,"Error Occured! Please try again.",Toast.LENGTH_SHORT).show();
					}

				}
			}
		});
	}
	public void bridge() {
		toolbar= (Toolbar) findViewById(R.id.toolbar);
		etname= (EditText) findViewById(R.id.et_name);
		etregisno= (EditText) findViewById(R.id.et_regisno);
		responseView= (TextView) findViewById(R.id.responseView);
		btlogin= (Button) findViewById(R.id.bt_login);
		progressBar= (ProgressBar) findViewById(R.id.progressBar);

	}

	class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

		private Exception exception;

		protected void onPreExecute() {
			progressBar.setVisibility(View.VISIBLE);
			responseView.setText("");
		}

		protected String doInBackground(Void... urls) {
			// Do some validation here

			try {
				URL url = new URL(API_URL + "username=" + name + "&userpass=" + reg_number + "&apikey=zxcvbnm123&type=1");
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				try {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
					StringBuilder stringBuilder = new StringBuilder();
					String line="";
					while ((line = bufferedReader.readLine()) != null) {
						stringBuilder.append(line).append("\n");
					}
					bufferedReader.close();
					return stringBuilder.toString();
				}
				finally{
					urlConnection.disconnect();
				}
			}
			catch(Exception e) {
				Log.e("ERROR", e.getMessage(), e);
				return null;
			}
		}

		protected void onPostExecute(String response) {
			String success="";
			String profileDetails[] ={"","","",""};
			if(response == null) {
				response = "THERE WAS AN ERROR";
			}
			progressBar.setVisibility(View.GONE);
			Log.i("INFO", response);
			try {
				JSONObject root=new JSONObject(response);
				success=root.getString("auth_value");
				String uid=root.getString("uid");
				profileDetails[0]=root.getString("username");
				profileDetails[1]=root.getString("user_img");
				profileDetails[2]=root.getString("dob");
				profileDetails[3]=root.getString("phone");
				if(success.equals("1")){
					Intent intent =new Intent(Login.this,ResourcesActivity.class);
					intent.putExtra("uid",uid);
					intent.putExtra("profile details",profileDetails);
					startActivity(intent);
				}else{
					responseView.setText("Invalid Name or Registration Number");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			finish();
		}

		return super.onOptionsItemSelected(item);
	}
}
