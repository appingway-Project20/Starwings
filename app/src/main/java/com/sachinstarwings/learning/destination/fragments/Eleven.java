package com.sachinstarwings.learning.destination.fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sachinstarwings.learning.destination.R;


public class Eleven extends Fragment{
	View myFragmentView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		myFragmentView=inflater.inflate(R.layout.fragment_eleven, container, false);
		return myFragmentView;
	}

}
