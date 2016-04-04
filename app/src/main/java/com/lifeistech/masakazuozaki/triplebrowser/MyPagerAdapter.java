package com.lifeistech.masakazuozaki.triplebrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by MasakazuOzaki on 2016/01/31.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    final String[] TITLES = {"TAB1","TAB2","TAB3"};
    public MyPagerAdapter (FragmentManager fm)  { super(fm); }

    @Override
    public CharSequence getPageTitle(int position){ return TITLES[position];}

    @Override
    public int getCount(){return TITLES.length;}

    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                return Fragment1.newInstance(position);
            case 1:
                return Fragment2.newInstance(position);
            default:
                return Fragment3.newInstance(position);

        }
    }

}
