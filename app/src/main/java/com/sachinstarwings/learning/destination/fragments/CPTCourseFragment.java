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
public class CPTCourseFragment extends Fragment {


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

    public CPTCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_course, container, false);
        unbinder = ButterKnife.bind(this, v);
        courseInfo1.setText("CPT Overview\n");
        courseInfo1.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo1.setTextSize(17);

        courseInfo2.setText("\nThis is the entrance examination which tests the students " +
                "aptitude level to become a chartered accountant.\n\n CA-CPt exam is divided" +
                " into two sessions of two hours each which includes Accounting, Mercantile " +
                "laws in first session and General Economics and Quantitative Aptitude in " +
                "second session. CPT is an objective type test with negative marking for each" +
                " wrong option. \n\n");
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
