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
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class PapersDisplayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void> {
    private static final String TAG = PapersDisplayActivity.class.getSimpleName();
    private static final int PAPERS_DOWNLOAD_LOADER = 100;
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final int WRITE_REQUEST_CODE = 43;
    private static final String API_URL = "http://starwing.appingway.com/php/web_api/";
    private String fileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers_display);
        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
            fileUrl = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        checkPermission();
    }

    @Override
    public Loader<Void> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Void>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Override
            public Void loadInBackground() {
                fileUrl = API_URL + fileUrl;
                Log.d(TAG, "file url: " + fileUrl);
                String fileName = "test.pdf";

                String extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
                Log.d(TAG, "external storage directory: " + extStorageDirectory);
//				String storagePath = null;
//				try {
//					storagePath = PathUtil.getPath(Downloads.this, Uri.parse(extStorageDirectory));
//					Log.d(TAG, "path: " + storagePath);
//				} catch (URISyntaxException e) {
//					e.printStackTrace();
//				}
                File folder = new File(extStorageDirectory, "papers");
                folder.mkdir();

                File pdfFile = new File(folder, fileName);

                try {
                    pdfFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileDownloader.downloadFile(fileUrl, pdfFile);
                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Void> loader, Void data) {
        showFile();
    }

    @Override
    public void onLoaderReset(Loader<Void> loader) {

    }
    private void fetchPaperFilesQuery(){
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, API_URL);

        LoaderManager loaderManager = getSupportLoaderManager();

        loaderManager.initLoader(PAPERS_DOWNLOAD_LOADER, bundle, this);
    }
    private void showFile(){
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/papers/" + "test.pdf");  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Log.d(TAG, "file path: " + path);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(PapersDisplayActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }
    void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //if permission granted, initialize the views
            if(!fileExistance("test.pdf")){
                fetchPaperFilesQuery();
            }
            else {
                showFile();
            }
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
                        fetchPaperFilesQuery();
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
        String path = Environment.getExternalStorageDirectory() + "/papers/" + fileName;
        File file = new File(path);
        Log.d(TAG, "path check:" + path);
        return file.exists();
    }
}
