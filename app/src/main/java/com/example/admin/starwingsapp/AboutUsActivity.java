package com.example.admin.starwingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.AboutUsAdapter;

public class AboutUsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AboutUsAdapter mAdapter;
    Toolbar toolbar;
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        titleView = (TextView)toolbar.findViewById(R.id.title);
        titleView.setText("About Us");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUsActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String text = " SLD Pvt. Ltd. is a venture of SACHIN KAUSHIK.\nSACHIN KAUSHIK started to teach in 2009 with opening a GLAMOUR " +
                "CLASSES.\n SLD exclusively provides classes for commerce students.\nSLD provides face to face classes as well as satellite" +
                "/virtual classes.\n It provides classes for CA, CS, B.COM, M.COM, BBA, MBA, 11th & 12th classes.\n Our prestigious faculties " +
                "have been successful in an uninterrupted and dedicated task ,i.e., perfectly shaping worthy leader as excellent CA, CS" +
                ", Graduation & Post-graduation, good human beings and Professionally Competent citizen.\n We work towards discovering and " +
                "rejuvenating the latent potentialities of students, making them realise their untapped skills and helping them streamline " +
                "their dynamism towards productive outcomes.";
        mAdapter = new AboutUsAdapter(text);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
