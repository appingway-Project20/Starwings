package com.example.admin.starwingsapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class OurCourses extends FragmentActivity implements ActionBar.TabListener {
	ActionBar bar;
	ViewPager viewpager;
	FragmentPageAdapter ft;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_our_courses);
		viewpager = (ViewPager) findViewById(R.id.pager);

		ft = new FragmentPageAdapter(getSupportFragmentManager(),
				this.getApplicationContext());
		viewpager.setAdapter(ft);
		
		final ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
		bar.setTitle("Our Courses");
		bar.setDisplayUseLogoEnabled(false);
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayShowHomeEnabled(false);
		bar.addTab(bar.newTab().setText("CPT").setTabListener(this));
		bar.addTab(bar.newTab().setText("IPCC").setTabListener(this));
		bar.addTab(bar.newTab().setText("FINAL").setTabListener(this));
		bar.addTab(bar.newTab().setText("IFRS").setTabListener(this));
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				bar.setSelectedNavigationItem(arg0);

				Fragment fragment = ((FragmentPageAdapter) viewpager
						.getAdapter()).getItem(arg0);

				if (arg0 <4 && fragment != null) {
					fragment.onResume();
				}

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			
			}
		});
	}


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}
