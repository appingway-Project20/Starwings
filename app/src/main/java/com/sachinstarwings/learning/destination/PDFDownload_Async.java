package com.sachinstarwings.learning.destination;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AKASH on 07-07-2017.
 */

public class PDFDownload_Async extends AsyncTask<String,Void,Integer>{

    Activity context;
    String title;
    public PDFDownload_Async(Activity context,String title){
       this.context=context;
        this.title=title;
    }

    @Override
    protected Integer doInBackground(String... params) {
            try {

                String fileName=title;
                String fileExtension=".pdf";

//           download pdf file.

                URL url = new URL(params[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();
                String PATH = Environment.getExternalStorageDirectory() + "/mydownload/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, fileName+fileExtension);
                FileOutputStream fos = new FileOutputStream(outputFile);
                InputStream is = c.getInputStream();
                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }
                fos.close();
                is.close();

                Log.d("TAG", "doInBackground:  pdf downloaded--ok--"+params[0]);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }

    }

    @Override
    protected void onPostExecute(Integer aVoid) {
        super.onPostExecute(aVoid);
        if(aVoid==1){
            Toast.makeText(context, "Downloading Completed", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context, "Downloading Failed", Toast.LENGTH_SHORT).show();
    }
}
