package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.ContactInfoAdapter;

public class ContactInfoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // same layout file as About Us screen
        setContentView(R.layout.about_us);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView title = (TextView)toolbar.findViewById(R.id.title);
        title.setText("Contact us");
        View view = toolbar.findViewById(R.id.dashboard);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactInfoActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactInfoAdapter();
        mRecyclerView.setAdapter(adapter);
    }
}
