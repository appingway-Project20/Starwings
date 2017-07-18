package com.example.admin.starwingsapp;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class Downloads extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
	Toast t;
	Toolbar toolbar;
	TextView title;
	private static final int NOTES_LOADER = 100;
	private static final String API_URL = "http://starwing.appingway.com/php/app_api/apiTopic.php";
	private static final String SEARCH_QUERY_URL_EXTRA = "query";
	private static final String API_KEY = "zxcvbnm123zxdewas";
	private static final int WRITE_REQUEST_CODE = 43;
	private static final String FILE_URL = "http://starwing.appingway.com/php/web_api/uploads/5962f9b499909statusmessages.pdf";
	private ProgressBar progressBar;

	View fileContainerView;
	TextView fileDownloadStatusTv;

	private static final String TAG = Downloads.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downloads);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		title = (TextView) toolbar.findViewById(R.id.title);
		title.setText("Downloads");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		View v = toolbar.findViewById(R.id.dashboard);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Downloads.this, Dashboard.class);
				startActivity(intent);
				finish();
			}
		});
		Toast t = Toast.makeText(Downloads.this,
				"My downloads", Toast.LENGTH_SHORT);
		t.show();
		initViews();
		checkPermission();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// handle arrow click here
		if (item.getItemId() == android.R.id.home) {
			finish(); // close this activity and return to preview activity (if there is any)
		}

		return super.onOptionsItemSelected(item);
	}


	@Override
	public Loader<String> onCreateLoader(int id, final Bundle args) {
		Log.d(TAG,"Loader started");
		return new AsyncTaskLoader<String>(this) {
			@Override
			protected void onStartLoading() {
				super.onStartLoading();
				if (args == null)
					return;
				forceLoad();
			}

			@Override
			public String loadInBackground() {

				String response = null;
				String url = args.getString(SEARCH_QUERY_URL_EXTRA);
				Log.i(TAG, "url: " + url);
				if (url == null || TextUtils.isEmpty(url)) {
					return null;
				}
				try {
					url = url + "?apikey=" + API_KEY + "&chapter_id=50607512000907111103";

					response = HttpRequest.get(url).body();
					Log.d(TAG, "response: " + response);
				} catch (HttpRequest.HttpRequestException e) {
					e.printStackTrace();
				}
//				String fileUrl = "http://starwing.appingway.com/php/web_api/uploads/5962f9b499909statusmessages.pdf";
//				Log.d(TAG, "file url: " + fileUrl);
//				String fileName = "test.pdf";
//				String extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
//				Log.d(TAG, "external storage directory: " + extStorageDirectory);
////				String storagePath = null;
////				try {
////					storagePath = PathUtil.getPath(Downloads.this, Uri.parse(extStorageDirectory));
////					Log.d(TAG, "path: " + storagePath);
////				} catch (URISyntaxException e) {
////					e.printStackTrace();
////				}
//				File folder = new File(extStorageDirectory, "testthreepdf");
//				folder.mkdir();
////				createFile("application/pdf", fileName);
//				File pdfFile = new File(folder, fileName);
//
//				try {
//					pdfFile.createNewFile();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				FileDownloader.downloadFile(fileUrl, pdfFile);
				return response;

			}
		};
	}

	@Override
	public void onLoadFinished(Loader<String> loader, String data) {
		progressBar.setVisibility(View.INVISIBLE);
		//String url = parseJsonAndReturnFileUrl(data);
		//showFile();
//

		fileDownloadStatusTv.setText("Click here to open file: "+"test.pdf");
		fileContainerView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showFile();
			}
		});
	}


	@Override
	public void onLoaderReset(Loader<String> loader) {

	}

	private void fetchNotesQuery() {
		Bundle bundle = new Bundle();
		bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

		LoaderManager loaderManager = getSupportLoaderManager();
		loaderManager.initLoader(NOTES_LOADER, bundle, this);
	}

	private String parseJsonAndReturnFileUrl(String jsonData) {
		String fileUrl = null;
		try {
			JSONObject root = new JSONObject(jsonData);
			JSONArray topicsArray = root.getJSONArray("topic_data");
			JSONArray firstFileUrl = topicsArray.getJSONArray(0);
			fileUrl = firstFileUrl.getString(3);
			Log.d(TAG, "video path: " + fileUrl);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return fileUrl;

	}

	private void createFile(String mimeType, String fileName) {
		Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

		// Filter to only show results that can be "opened", such as
		// a file (as opposed to a list of contacts or timezones).
		intent.addCategory(Intent.CATEGORY_OPENABLE);

		// Create a file with the requested MIME type.
		intent.setType(mimeType);
		intent.putExtra(Intent.EXTRA_TITLE, fileName);
		startActivityForResult(intent, WRITE_REQUEST_CODE);
	}

	//Handling permissions for Android Marshmallow and above
	void checkPermission() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
			//if permission granted, initialize the views
//			if(!fileExistance("test.pdf")){
				fetchNotesQuery();
//			}
//			else {
//			showFile();
//			}
		} else {
			//show the dialog requesting to grant permission
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					if (!fileExistance("test.pdf")){
						fetchNotesQuery();
					}
					else
						showFile();
				} else {
					//permission is denied (this is the first time, when "never ask again" is not checked)
					if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
						finish();
					}
				}
		}
	}
	private boolean fileExistance(String fileName){
		String path = Environment.getExternalStorageDirectory() + "/testthreepdf/" + fileName;
		File file = new File(path);
		Log.d(TAG, "path check:" + path);
		return file.exists();
	}
	private void showFile(){
		File pdfFile = new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + "test.pdf");  // -> filename = maven.pdf
		Uri path = Uri.fromFile(pdfFile);
		Log.d(TAG, "file path: " + path);
		Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
		pdfIntent.setDataAndType(path, "application/pdf");
		pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		try {
			startActivity(pdfIntent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(Downloads.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
		}
	}
	private void initViews(){
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		title = (TextView) toolbar.findViewById(R.id.title);

		fileContainerView = findViewById(R.id.file_container);
		fileDownloadStatusTv = (TextView)findViewById(R.id.tvfiles);
	}
	private void updateContents(String data){
		String filePath = parseJsonAndReturnFileUrl(data);
		File file = new File(filePath);
		String fileName = file.getName();

		fileDownloadStatusTv.setText("Click here to open file: "+fileName);
		fileContainerView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(fileExistance("test.pdf")){
					showFile();

				}
				else {
					Toast.makeText(getApplicationContext(), "File doesn't exist!", Toast.LENGTH_SHORT).show();
				}
			}

		});
	}
}

