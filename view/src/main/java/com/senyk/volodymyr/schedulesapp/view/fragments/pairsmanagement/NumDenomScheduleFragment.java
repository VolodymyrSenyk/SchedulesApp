package com.senyk.volodymyr.schedulesapp.view.fragments.pairsmanagement;

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
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

public class NumDenomScheduleFragment extends BaseFragment {
    private static final String SCHEDULE_NAME_BUNDLE_KEY = "Schedule name bundle key";
    private static final String DAY_ORDINAL_BUNDLE_KEY = "Day ordinal number bundle key";

    private SchedulesNavigationSharedViewModel sharedViewModel;

    private ViewPager viewPager;

    public static NumDenomScheduleFragment newInstance(String scheduleName, int dayOrdinal) {
        NumDenomScheduleFragment newFragment = new NumDenomScheduleFragment();
        Bundle args = new Bundle();
        args.putString(SCHEDULE_NAME_BUNDLE_KEY, scheduleName);
        args.putInt(DAY_ORDINAL_BUNDLE_KEY, dayOrdinal);
        newFragment.setArguments(args);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_num_denom_schedule, container, false);
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
        Bundle args = getArguments();
        if (args != null) {
            for (int i = 0; i < weeksNames.length; i++) {
                adapter.addFragment(
                        OneDayScheduleFragment.newInstance(
                                args.getString(SCHEDULE_NAME_BUNDLE_KEY),
                                i + 1,
                                args.getInt(DAY_ORDINAL_BUNDLE_KEY)),
                        weeksNames[i]
                );
            }
        }
        this.viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(this.viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.viewPager.setCurrentItem(this.sharedViewModel.getCurrentWeekIndex());
    }

    @Override
    public void onPause() {
        super.onPause();
        this.sharedViewModel.saveCurrentWeekIndex(this.viewPager.getCurrentItem());
    }
}
