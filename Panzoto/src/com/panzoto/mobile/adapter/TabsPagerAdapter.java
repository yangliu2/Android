package com.panzoto.mobile.adapter;

import com.panzoto.android.*;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter implements ActionBar.TabListener{
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Actions fragment activity
            return new ActionsFragment();
        case 1:
            // Options fragment activity
            return new FamilyFragment();
        case 2:
            // Status fragment activity
            return new GamingFragment();
        case 3:
            // Status fragment activity
            return new SettingsFragment();
        }
 
        return null;
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
    
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}
