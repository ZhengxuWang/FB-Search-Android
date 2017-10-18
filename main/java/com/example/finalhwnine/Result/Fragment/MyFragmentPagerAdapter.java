package com.example.finalhwnine.Result.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by User on 4/25/2017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String key;
    private String[] mTitles = new String[]{"Users", "Pages","Events","Places","Groups"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentPagerAdapter(FragmentManager fm, String key) {
        super(fm);
        this.key = key;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("keyword", key);

        if (position == 1) {
            PagesFragment pagesFragment = new PagesFragment();
            pagesFragment.setArguments(bundle);
            return pagesFragment;
        } else if (position == 2) {
            EventsFragment eventsFragment = new EventsFragment();
            eventsFragment.setArguments(bundle);
            return eventsFragment;
        }else if (position==3){
            PlacesFragment placesFragment = new PlacesFragment();
            placesFragment.setArguments(bundle);
            return placesFragment;
        }else if(position==4){
            GroupsFragment groupsFragment = new GroupsFragment();
            groupsFragment.setArguments(bundle);
            return groupsFragment;
        }
        UsersFragment usersFragment = new UsersFragment();
        usersFragment.setArguments(bundle);
        return usersFragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

