package com.example.admin.starwingsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.NotificationAdapter;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by LALIT on 21-06-2017.
 */

public class NotificationActivity extends AppCompatActivity {
    RecyclerView rv;
    Toolbar toolbar;
    String message,file;
    ArrayList<String> arrayList;
    LinearLayoutManager layoutManager;
    TextView titleView;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    Switch switchnotify;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        init();
        titleView = (TextView) toolbar.findViewById(R.id.title);
        titleView.setText("Notifications");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(GCMSharedPreferences.SENT_TOKEN_TO_SERVER, false);
                String tkmsg = "\nRegistration ID from GCM: " +
                        sharedPreferences.getString(GCMSharedPreferences.REG_ID, "N/A");
                tkmsg = getString(R.string.gcm_send_message) + tkmsg;
                if (!intent.getExtras().getBoolean("register"))
                    tkmsg = getString(R.string.gcm_unregister_message);
                tkmsg = intent.getStringExtra("prefix") + tkmsg;
            }
        };
        prefs = getApplicationContext().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = prefs.edit();
        switchnotify.setChecked(prefs.getBoolean("isChecked", false));
        switchnotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startRegistrationService(true, false);
                } else {
                    startRegistrationService(false, false);
                }
                editor.putBoolean("isChecked", isChecked);
                editor.commit();
            }
        });
        //startRegistrationService(true, false);
        //GCM related part end
        message = getIntent().getStringExtra("message");
        arrayList=new ArrayList<String>(prefs.getStringSet("list",new HashSet<String>()));
        if (message!=null){
            arrayList.add(message);
        }
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new NotificationAdapter(arrayList, this));
    }
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMSharedPreferences.REGISTRATION_COMPLETE));
        //arrayList=new ArrayList<String>(prefs.getStringSet("list",new HashSet<String>()));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        editor.putStringSet("list",new HashSet<String>(arrayList));
        editor.commit();
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        arrayList=new ArrayList<>();
        rv= (RecyclerView) findViewById(R.id.notrv);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        switchnotify = (Switch) findViewById(R.id.switchnotify);
    }

    public void startRegistrationService(boolean reg, boolean tkr) {

        if (GCMCommonUtils.checkPlayServices(this)) {
            Intent intent = new Intent(this, MyGCMRegistrationIntentService.class);
            intent.putExtra("register", reg);
            intent.putExtra("tokenRefreshed", tkr);
            startService(intent);
        }

    }
}
