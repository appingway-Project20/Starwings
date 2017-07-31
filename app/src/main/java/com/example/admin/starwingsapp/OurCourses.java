package com.example.admin.starwingsapp;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admin.starwingsapp.adpaters.FragmentPageAdapter;
import com.example.admin.starwingsapp.fragments.Eleven;
import com.example.admin.starwingsapp.fragments.Twelve;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;


public class OurCourses extends AppCompatActivity {
	ActionBar bar;
	Toolbar toolbar;
	ViewPager viewpager;
	TabLayout tabLayout;
	FragmentPageAdapter ft;
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_our_courses);
		viewpager = (ViewPager) findViewById(R.id.pager);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		title= (TextView) toolbar.findViewById(R.id.title);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		toolbar.setTitle("Courses");
		View v = toolbar.findViewById(R.id.dashboard);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(OurCourses.this,Dashboard.class);
				startActivity(intent);
			}
		});

		ft = new FragmentPageAdapter(getSupportFragmentManager(),
				this.getApplicationContext());
		viewpager.setAdapter(ft);

		SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
		viewPagerTab.setViewPager(viewpager);


//		tabLayout = (TabLayout) findViewById(R.id.tabs);
//		tabLayout.setupWithViewPager(viewpager);


	/*	PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		tabsStrip.setUnderlineColor(Color.parseColor("#000000"));*/



	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// handle arrow click here
		if (item.getItemId() == android.R.id.home) {
			finish(); // close this activity and return to preview activity (if there is any)
		}

		return super.onOptionsItemSelected(item);
	}
	private void setupWithViewPager(ViewPager viewPager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFragment(new Eleven(), "11th CLASS ACCOUNTS");
		adapter.addFragment(new Twelve(), "12th CLASS ACCOUNTS");
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
