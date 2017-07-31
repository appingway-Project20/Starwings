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
public class IFRSFragment extends Fragment {


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

    public IFRSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_course, container, false);
        unbinder = ButterKnife.bind(this, v);

        courseInfo1.setText("\nTraining program on Diploma in IFRS");
        courseInfo1.setTypeface(Typeface.DEFAULT_BOLD);
        courseInfo1.setTextSize(17);

        courseInfo2.setText("\nInternational Financial Reporting Standards (IFRS) has become" +
                " the benchmark for accounting globally. IFRS has also gained momentum in " +
                "India post issuance of notification by MCA requiring conversion with IFRS " +
                "i.e Ind AS by Indian companies from 2016-17 ");
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
