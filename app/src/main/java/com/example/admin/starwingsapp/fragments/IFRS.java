package com.example.admin.starwingsapp.fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.starwingsapp.R;

public class IFRS extends Fragment{
	View myFragmentView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		myFragmentView=inflater.inflate(R.layout.fragment_ifrs, container, false);
		return myFragmentView;
	}

}
