package com.example.admin.starwingsapp.adpaters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.starwingsapp.fragments.Eleven;
import com.example.admin.starwingsapp.fragments.Twelve;

public class FragmentPageAdapter extends FragmentPagerAdapter {

	public String name[]={"11th CLASS ACCOUNTS","12th CLASS ACCOUNTS"};
	public FragmentPageAdapter(FragmentManager fm, Context context) {
		super(fm);

	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new Eleven();

		case 1:
			return new Twelve();

		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return name[position];
	}
}
