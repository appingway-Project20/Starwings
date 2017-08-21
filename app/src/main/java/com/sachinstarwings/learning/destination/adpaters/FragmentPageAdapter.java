package com.sachinstarwings.learning.destination.adpaters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sachinstarwings.learning.destination.fragments.BBAFragment;
import com.sachinstarwings.learning.destination.fragments.BcomFragment;
import com.sachinstarwings.learning.destination.fragments.CPTCourseFragment;
import com.sachinstarwings.learning.destination.fragments.CaCourseFragment;
import com.sachinstarwings.learning.destination.fragments.CsCourseFragment;
import com.sachinstarwings.learning.destination.fragments.Eleven;
import com.sachinstarwings.learning.destination.fragments.FinalFragment;
import com.sachinstarwings.learning.destination.fragments.IFRSFragment;
import com.sachinstarwings.learning.destination.fragments.IPCCCourseFragment;
import com.sachinstarwings.learning.destination.fragments.MBAFragment;
import com.sachinstarwings.learning.destination.fragments.McomFragment;
import com.sachinstarwings.learning.destination.fragments.Twelve;

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
