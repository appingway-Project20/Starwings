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

public class ACCACourseActivity extends AppCompatActivity implements BatchesAdapter.ListItemClickListener {
    String[] courses = {"F5 Performance Management","F7 Financial Reporting"};
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //same layout file as About Us screen.
        setContentView(R.layout.about_us);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BatchesAdapter(courses,this);

        mRecyclerView.setAdapter(adapter);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView title = (TextView)toolbar.findViewById(R.id.title);
        title.setText("Courses");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ACCACourseActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onListItemClicked(int position) {
        Intent intent;
        switch (position){
            case 0:
                 intent = new Intent(ACCACourseActivity.this,BatchDetailsActivity.class);
                startActivity(intent);
                break;
            case 1:
                 intent = new Intent(ACCACourseActivity.this,BatchDetailsActivity.class);
                startActivity(intent);
        }

    }
}
