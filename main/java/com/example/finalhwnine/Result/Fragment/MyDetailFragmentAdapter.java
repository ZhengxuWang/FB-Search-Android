package com.example.finalhwnine.Result.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by User on 4/25/2017.
 */

public class MyDetailFragmentAdapter extends FragmentPagerAdapter {

    private String id;
    private String[] mTitles = new String[]{"Albums","Posts"};

    public MyDetailFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyDetailFragmentAdapter(FragmentManager fm, String id) {
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);

        if (position == 1) {
            PostsFragment postsFragment = new PostsFragment();
            postsFragment.setArguments(bundle);
            return postsFragment;
        }
        AlbumsFragment albumsFragment = new AlbumsFragment();
        albumsFragment.setArguments(bundle);
        return albumsFragment;
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

