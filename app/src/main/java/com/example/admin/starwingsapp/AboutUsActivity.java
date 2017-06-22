package com.example.admin.starwingsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.AboutUsAdapter;

public class AboutUsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AboutUsAdapter mAdapter;
    Toolbar toolbar;
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        setTitle("About Us");
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleView = (TextView)toolbar.findViewById(R.id.title);
        titleView.setText("About Us");
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus nec risus nec" +
                " dolor varius placerat. Suspendisse quis massa tempor nunc viverra ultricies id nec" +
                " nisl. Praesent in vehicula urna. Nullam condimentum odio lectus. Duis tempor sit " +
                "amet mauris non bibendum. Proin erat massa, vestibulum vulputate erat ut, dapibus " +
                " sollicitudin tellus, quis tincidunt massa hendrerit at.";
        mAdapter = new AboutUsAdapter(text);
        mRecyclerView.setAdapter(mAdapter);
    }
}
