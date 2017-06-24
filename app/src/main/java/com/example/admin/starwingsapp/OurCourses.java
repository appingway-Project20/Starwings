package com.example.admin.starwingsapp;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.example.admin.starwingsapp.adpaters.FragmentPageAdapter;
import com.example.admin.starwingsapp.fragments.CPT;
import com.example.admin.starwingsapp.fragments.FINAL;
import com.example.admin.starwingsapp.fragments.IFRS;
import com.example.admin.starwingsapp.fragments.IPCC;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;


public class OurCourses extends AppCompatActivity {
	ActionBar bar;
	Toolbar toolbar;
	ViewPager viewpager;
	TabLayout tabLayout;
	FragmentPageAdapter ft;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_our_courses);
		viewpager = (ViewPager) findViewById(R.id.pager);
		toolbar = (Toolbar) findViewById(R.id.toolbar);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setTitle("Courses");

		ft = new FragmentPageAdapter(getSupportFragmentManager(),
				this.getApplicationContext());
		viewpager.setAdapter(ft);


		tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewpager);


	/*	PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		tabsStrip.setUnderlineColor(Color.parseColor("#000000"));*/

		/*SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
		viewPagerTab.setViewPager(viewpager);*/

	}
	private void setupWithViewPager(ViewPager viewPager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFragment(new CPT(), "CPT");
		adapter.addFragment(new FINAL(), "FINAL");
		adapter.addFragment(new IFRS(), "IFRS");
		adapter.addFragment(new IPCC(), "IPCC");
		viewPager.setAdapter(adapter);
	}

	class ViewPagerAdapter extends FragmentPagerAdapter {
		private final List<Fragment> mFragmentList = new ArrayList<>();
		private final List<String> mFragmentTitleList = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager manager) {
			super(manager);
		}

		@Override
		public Fragment getItem(int position) {
			return mFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return mFragmentList.size();
		}

		public void addFragment(Fragment fragment, String title) {
			mFragmentList.add(fragment);
			mFragmentTitleList.add(title);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentTitleList.get(position);
		}
	}

}
