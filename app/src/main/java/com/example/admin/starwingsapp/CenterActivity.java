package com.example.admin.starwingsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.CentreAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AKASH on 22-06-2017.
 */

public class CenterActivity extends AppCompatActivity {
    RecyclerView cenrv;
    Toolbar toolbar;
    TextView toolbartitle;
    LinearLayoutManager layoutManager;
    List<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centre_layout);

        init();
        toolbartitle.setText("States");
        setSupportActionBar(toolbar);
        layoutManager=new LinearLayoutManager(this);
        cenrv.setLayoutManager(layoutManager);
        List<String> Centres = Arrays.asList(getResources().getStringArray(R.array.Centres));

        cenrv.setAdapter(new CentreAdapter(arrayList,this));
        for(String item : Centres){
            arrayList.add(item);
            cenrv.getAdapter().notifyDataSetChanged();
        }


    }

    private void init() {

        cenrv= (RecyclerView) findViewById(R.id.cenrv);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbartitle= (TextView) toolbar.findViewById(R.id.title);
    }
}
