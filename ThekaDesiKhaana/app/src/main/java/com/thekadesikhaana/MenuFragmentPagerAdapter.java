package com.thekadesikhaana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ashishchoudhary on 05/02/17.
 */

public class MenuFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "NorthIndian", "Bengali", "Odia" };
    private Bundle bundle;

    public MenuFragmentPagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.bundle = bundle;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
//        bundle.putInt(ARG_PAGE,position + 1);
        return MenuFragment.newInstance(bundle);
    }

    @Override
    public int getItemPosition(Object object) {
        if(object instanceof MenuFragmentPagerAdapter) {

        }
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    public Bundle getBundle()
    {
        return bundle;
    }
}
