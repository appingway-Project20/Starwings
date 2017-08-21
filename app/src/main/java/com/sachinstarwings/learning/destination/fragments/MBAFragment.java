package com.sachinstarwings.learning.destination.fragments;


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
public class MBAFragment extends Fragment {


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

    public MBAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_course, container, false);
        unbinder = ButterKnife.bind(this, v);

        courseInfo2.setText(" 1. Finance");
        courseInfo2.setTextSize(18);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
