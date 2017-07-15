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
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.starwingsapp.adpaters.NotificationAdapter;
import com.example.admin.starwingsapp.models.NotificationData;

import java.util.ArrayList;

/**
 * Created by AKASH on 21-06-2017.
 */

public class NotificationActivity extends AppCompatActivity {
    RecyclerView rv;
    Toolbar toolbar;
    String message;
    ArrayList<NotificationData> arrayList;
    LinearLayoutManager layoutManager;
    TextView titleView;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        init();
        titleView = (TextView)toolbar.findViewById(R.id.title);
        titleView.setText("Notifications");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this,Dashboard.class);
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
                if (sentToken) {
                    //mInformationTextView.setText(tkmsg);
                } else {
                    //mInformationTextView.setText(getString(R.string.token_error_message) + tkmsg);
                }
            }
        };
        //mInformationTextView = (TextView) findViewById(R.id.informationTextView);

        startRegistrationService(true, false);
        //GCM related part end

        layoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        message=getIntent().getStringExtra("message");
        arrayList.add(new NotificationData("This is the title",message));
        rv.setAdapter(new NotificationAdapter(arrayList,this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMSharedPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    /*@Override
    public void onClick(View view) {
        if (view.getId() == R.id.ret_btn) {
            startRegistrationService(true, true);
        }
        if (view.getId() == R.id.unreg_btn) {
            //try to unregister
            startRegistrationService(false, false);
        }

    }*/

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
    }

    public void startRegistrationService(boolean reg, boolean tkr) {

        if (GCMCommonUtils.checkPlayServices(this)) {
            //mRegistrationProgressBar = (ProgressBar) findViewById(R.id.registrationProgressBar);
            //mRegistrationProgressBar.setVisibility(View.VISIBLE);
            //TextView tv = (TextView) findViewById(R.id.informationTextView);
            //if (reg) tv.setText(R.string.registering_message);
            //else tv.setText(R.string.unregistering_message);
            Toast.makeText(this, "Background service started...", Toast.LENGTH_LONG).show();
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, MyGCMRegistrationIntentService.class);
            intent.putExtra("register", reg);
            intent.putExtra("tokenRefreshed", tkr);
            startService(intent);
        }

    }
}
