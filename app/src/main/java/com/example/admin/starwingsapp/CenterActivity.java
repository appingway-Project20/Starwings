package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.CentreAdapter;

import java.util.ArrayList;

/**
 * Created by AKASH on 22-06-2017.
 */

public class CenterActivity extends AppCompatActivity {
    RecyclerView cenrv;
    Toolbar toolbar;
    TextView toolbartitle;
    LinearLayoutManager layoutManager;
    ArrayList<String> arrayList=new ArrayList<String>();
    String[] centres={"Dwarka Ramphal Chowk","Laxmi Nagar Near Metrostation",
            "Pitam Pura near Metrostation","Janak Puri"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centre_layout);

        init();
        toolbartitle.setText("Centres");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CenterActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
        layoutManager=new LinearLayoutManager(this);
        cenrv.setLayoutManager(layoutManager);
        //List<String> Centres = Arrays.asList(getResources().getStringArray(R.array.Centres));
        cenrv.setAdapter(new CentreAdapter(arrayList,this));

        for(int i=0; i<4; i++){
            arrayList.add(centres[i]);
            cenrv.getAdapter().notifyDataSetChanged();
        }


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
        cenrv= (RecyclerView) findViewById(R.id.cenrv);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbartitle= (TextView) toolbar.findViewById(R.id.title);
    }
}
