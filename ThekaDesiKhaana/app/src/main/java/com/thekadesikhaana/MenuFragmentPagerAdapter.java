package com.thekadesikhaana;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import model.Bengali;
import model.FoodType;
import model.FoodTypeResponseModel;
import model.NorthIndian;
import model.Odia;
import model.Punjabi;

/**
 * Created by ashishchoudhary on 05/02/17.
 */
class MenuFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = MenuFragmentPagerAdapter.class.getSimpleName();
    private int PAGE_COUNT = -1;
    private List<FoodType> mFoodList;

    MenuFragmentPagerAdapter(FragmentManager fm, FoodTypeResponseModel model) {
        super(fm);
        PAGE_COUNT = model.getFoodType().size();
        mFoodList = model.getFoodType();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                Bengali bengaliFood = mFoodList.get(position).getBengali();
                MenuFragment fragment = MenuFragment.newInstance();
                fragment.setFoodStyle(bengaliFood);
                return fragment;
            case 1:
                NorthIndian northIndian = mFoodList.get(position).getNorthIndian();
                MenuFragment northIndiaFrag = MenuFragment.newInstance();
                northIndiaFrag.setFoodStyle(northIndian);
                return northIndiaFrag;
            case 2:
                Odia odia = mFoodList.get(position).getOdia();
                MenuFragment odiaFrag = MenuFragment.newInstance();
                odiaFrag.setFoodStyle(odia);
                return odiaFrag;
            case 3:
                Punjabi punjabi = mFoodList.get(position).getPunjabi();
                MenuFragment punjabiFrag = MenuFragment.newInstance();
                punjabiFrag.setFoodStyle(punjabi);
                return punjabiFrag;
            default:
                Log.d(TAG, "WRONG CHOICE");
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mFoodList.get(position).getBengali().getName();
            case 1:
                return /*mFoodList.get(position).getNorthIndian().getName();*/"NORTH";
            case 2:
                return mFoodList.get(position).getOdia().getName();
            case 3:
                return mFoodList.get(position).getPunjabi().getName();
            default:
                return "";
        }
    }

}
