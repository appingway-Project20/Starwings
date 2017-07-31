package com.example.admin.starwingsapp.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.starwingsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaCourseFragment extends Fragment {



    Unbinder unbinder;
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

    public CaCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ca_course, container, false);
        unbinder = ButterKnife.bind(this, view);
        courseInfo1.setText("CPT Overview\n");
        courseInfo1.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo1.setTextSize(17);

        courseInfo2.setText("\nThis is the entrance examination which tests the students " +
                "aptitude level to become a chartered accountant.\n\n CA-CPt exam is divided" +
                " into two sessions of two hours each which includes Accounting, Mercantile " +
                "laws in first session and General Economics and Quantitative Aptitude in " +
                "second session. CPT is an objective type test with negative marking for each" +
                " wrong option. \n\n" +
                "Integrated Professional Competence Course is the second level " +
                "of CA examination After clearing the cpt test students have to give the ipcc" +
                "exam. This exam is divided into two groups or sections.Both these groups can" +
                "be studied separately and combined\n");
        courseInfo3.setText("\nTraining program on Diploma in IFRS");
        courseInfo3.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo3.setTextSize(17);

        courseInfo4.setText("\nInternational Financial Reporting Standards (IFRS) has become" +
                " the benchmark for accounting globally. IFRS has also gained momentum in " +
                "India post issuance of notification by MCA requiring conversion with IFRS " +
                "i.e Ind AS by Indian companies from 2016-17 ");

        courseInfo5.setText("FINAL OVERVIEW");
        courseInfo5.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo5.setTextSize(17);

        courseInfo6.setText("FINAL is the third level of CA examination. After clearing the " +
                "IPCC (both groups) students have to give the FINAL exam. This exam is " +
                "divided into two groups. Both these groups can be studied separately or " +
                "combined.");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
