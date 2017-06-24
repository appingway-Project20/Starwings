package com.example.admin.starwingsapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.BatchesAdapter;

public class BatchesActivity extends AppCompatActivity implements BatchesAdapter.ListItemClickListener {
String[] courses = {"ACCA","C.A","Excel","G.S.T"};
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using same layout file as About Us screen
        setContentView(R.layout.about_us);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //mRecyclerView.setItemAnimator(new SlideInRightAnimator());


        // This is important. Setting recyclerView's alpha to zero.
// Basically this is just to hide recyclerview at start before binding data
// As setVisibility is not working on recycler view object.
        mRecyclerView.setAlpha(0);
        adapter = new BatchesAdapter(courses,this);
//        SlideInRightAnimationAdapter slideInRightAnimationAdapter = new SlideInRightAnimationAdapter(adapter);
//        slideInRightAnimationAdapter.setFirstOnly(true);
//        slideInRightAnimationAdapter.setDuration(1000);
//        slideInRightAnimationAdapter.setInterpolator(new OvershootInterpolator());
        mRecyclerView.setAdapter(adapter);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView title = (TextView)toolbar.findViewById(R.id.title);
        title.setText("Courses");
        View v = toolbar.findViewById(R.id.dashboard);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BatchesActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });

        // ********************* Animate at start ********************************

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // This will give me the initial first and last visible element's position.
                // This is required as only this elements needs to be animated
                // Start will be always zero in this case as we are calling in onCreate
                int start = linearLayoutManager.findFirstVisibleItemPosition();
                int end = linearLayoutManager.findLastVisibleItemPosition();

                Log.i("Start: ", start + "");
                Log.i("End: ", end + "");

                // Multiplication factor
                int DELAY = 1000;

                // Loop through all visible element
                for (int i = start; i <= end; i++) {
                    Log.i("Animatining: ", i + "");

                    // Get View
                    View v = mRecyclerView.findViewHolderForAdapterPosition(i).itemView;

                    // Hide that view initially
                    v.setAlpha(0);
                    // Setting animations: slide and alpha 0 to 1
                    PropertyValuesHolder slide = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 150, 0);
                    PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0, 1);
                    ObjectAnimator a = ObjectAnimator.ofPropertyValuesHolder(v, slide, alpha);
                    a.setDuration(300);

                    // It will set delay. As loop progress it will increment
                    // And it will look like items are appearing one by one.
                    // Not all at a time
                    a.setStartDelay(i * DELAY);

                    a.setInterpolator(new DecelerateInterpolator());

                    a.start();

                }

                // Set Recycler View visible as all visible are now hidden
                // Animation will start, so set it visible
                mRecyclerView.setAlpha(1);

            }
        }, 50);


    }

    @Override
    public void onListItemClicked(int position) {
        Intent intent;
        switch (position){
            case 0:
                 intent = new Intent(BatchesActivity.this,ACCACourseActivity.class);
                startActivity(intent);break;
            case 1:
                 intent = new Intent(BatchesActivity.this,CAActivity.class);
                startActivity(intent);break;
            case 2:
                intent = new Intent(BatchesActivity.this,ExcelCourseActivity.class);
                startActivity(intent);break;
            case 3:
                intent = new Intent(BatchesActivity.this,GSTCourseActivity.class);
                startActivity(intent);break;

        }

    }
}
