package com.senyk.volodymyr.schedulesapp.view.adapters.viewpager.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public abstract class BaseViewPagerAdapter extends FragmentPagerAdapter {
    public BaseViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
}
