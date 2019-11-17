package com.senyk.volodymyr.schedulesapp.view.adapters.viewpager.twodays;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.senyk.volodymyr.schedulesapp.view.adapters.viewpager.base.BaseViewPagerAdapter;

public class TwoDaysViewPagerAdapter extends BaseViewPagerAdapter {
    public TwoDaysViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
