package com.senyk.volodymyr.schedulesapp.view.fragments.schedule.twoweekformatday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapters.viewpager.SimpleTabAdapter;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.view.fragments.schedule.day.DayScheduleFragment;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

public class TwoWeekFormatDayScheduleFragment extends BaseFragment {
    private SchedulesNavigationSharedViewModel sharedViewModel;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two_days, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);

        this.viewPager = view.findViewById(R.id.days_pager);
        TabLayout tabLayout = view.findViewById(R.id.days_tabs);
        SimpleTabAdapter adapter = new SimpleTabAdapter(getChildFragmentManager());
        String[] weeksNames = getResources().getStringArray(R.array.weeks_names);
        for (String weekName : weeksNames) {
            adapter.addFragment(new DayScheduleFragment(), weekName);
        }
        this.viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                sharedViewModel.setCurrentWeekIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharedViewModel.getCurrentScheduleIndexes().getValue() != null) {
            this.viewPager.setCurrentItem(sharedViewModel.getCurrentScheduleIndexes().getValue().first);
        } else {
            this.viewPager.setCurrentItem(0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.sharedViewModel.setCurrentWeekIndex(viewPager.getCurrentItem());
    }
}
