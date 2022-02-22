package com.myappinternship.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.myappinternship.fragments.GalleryFragment;
import com.myappinternship.fragments.HomeFragment;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public MyPagerAdapter(FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager, tabCount);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return  homeFragment;
            case 1:

                GalleryFragment galleryFragment = new GalleryFragment();
                return  galleryFragment;

            case 2:

                HomeFragment homeFragment1 = new HomeFragment();
                return  homeFragment1;

        }

        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
