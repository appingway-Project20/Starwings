package com.sachinstarwings.learning.destination.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CsCourseFragment extends Fragment {


    @BindView(R.id.course_info_1)
    TextView courseInfo1;
    @BindView(R.id.course_info_2)
    TextView courseInfo2;
    @BindView(R.id.course_info_3)
    TextView courseInfo3;
    @BindView(R.id.course_info_4)
    TextView courseInfo4;
    @BindView(R.id.course_info_5)
    TextView courseInfo5;
    @BindView(R.id.course_info_6)
    TextView courseInfo6;
    Unbinder unbinder;

    public CsCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_course, container, false);
        unbinder = ButterKnife.bind(this, v);
        courseInfo1.setText("Foundation");
        courseInfo1.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo1.setTextSize(17);

        courseInfo2.setText("\nFoundation Programme which is of eight months duration can be" +
                "pursued by 10+2 pass or equivalent students of Arts, Science or Commerce " +
                "stream (Excluding Fine Arts)");

        courseInfo3.setText("\nExecutive");
        courseInfo3.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo3.setTextSize(17);

        courseInfo4.setText("Executive Programme can be pursued by a Graduate of all streams" +
                " except Fine Arts.\n");

        courseInfo5.setText("Professional\n");
        courseInfo5.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo5.setTextSize(17);

        courseInfo6.setText("Professional Programme can be pursued only after clearing the" +
                " Executive Programme of CS Course\n");
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
