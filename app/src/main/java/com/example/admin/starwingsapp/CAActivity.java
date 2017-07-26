package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.BatchesAdapter;

public class CAActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using same layout file as About Us screen.
        setContentView(R.layout.about_us);

        String courses[] = {"F5 Performance Management","F7 Financial Reporting", "Eleven(old course)"
                            ,"Final - 1(old course)","Final - 3(old course)","FINAL - 1(New Course)"
                            ,"FINAL - 2(New Course)","Foundation (New  Course)",
                            "Intermediate -1 (New Course)","Intermediate -2 (New Course",
                            "Twelve - 1(old course)","Twelve - 2(old course)"};

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BatchesAdapter(courses);

        mRecyclerView.setAdapter(adapter);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView title = (TextView)toolbar.findViewById(R.id.title);
        title.setText("Courses");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CAActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });

    }
}
