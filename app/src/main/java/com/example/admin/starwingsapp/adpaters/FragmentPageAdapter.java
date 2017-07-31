package com.example.admin.starwingsapp.adpaters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.starwingsapp.fragments.BBAFragment;
import com.example.admin.starwingsapp.fragments.BcomFragment;
import com.example.admin.starwingsapp.fragments.CPTCourseFragment;
import com.example.admin.starwingsapp.fragments.CaCourseFragment;
import com.example.admin.starwingsapp.fragments.CsCourseFragment;
import com.example.admin.starwingsapp.fragments.Eleven;
import com.example.admin.starwingsapp.fragments.FinalFragment;
import com.example.admin.starwingsapp.fragments.IFRSFragment;
import com.example.admin.starwingsapp.fragments.IPCCCourseFragment;
import com.example.admin.starwingsapp.fragments.MBAFragment;
import com.example.admin.starwingsapp.fragments.McomFragment;
import com.example.admin.starwingsapp.fragments.Twelve;

public class FragmentPageAdapter extends FragmentPagerAdapter {

	public String name[]={"11th CLASS ACCOUNTS","12th CLASS ACCOUNTS", "CA", "CS", "CPT", "IPCC","FINAL","IFRS","B.COM","M.COM","MBA","BBA"};
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

			case 2:
				return new CaCourseFragment();
			case 3:
				return new CsCourseFragment();
			case 4:
				return new CPTCourseFragment();
			case 5:
				return new IPCCCourseFragment();
			case 6:
				return new FinalFragment();
			case 7:
				return new IFRSFragment();
			case 8:
				return new BcomFragment();
			case 9:
				return new McomFragment();
			case 10:
				return new MBAFragment();
			case 11:
				return new BBAFragment();


		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return name.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return name[position];
	}
}
