package com.example.admin.starwingsapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapter extends FragmentPagerAdapter {

	public FragmentPageAdapter(FragmentManager fm, Context context) {
		super(fm);

	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new CPT();

		case 1:
			return new IPCC();

		case 2:
			return new FINAL();
			
		case 3:
			return new IFRS();

		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
