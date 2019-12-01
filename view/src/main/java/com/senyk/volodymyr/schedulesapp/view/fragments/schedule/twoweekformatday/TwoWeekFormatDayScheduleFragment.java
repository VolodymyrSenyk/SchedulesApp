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
    private static final String SCHEDULE_NAME_BUNDLE_KEY = "Schedule name bundle key";
    private static final String DAY_ORDINAL_BUNDLE_KEY = "Day ordinal number bundle key";

    private SchedulesNavigationSharedViewModel sharedViewModel;
    private ViewPager viewPager;

    public static TwoWeekFormatDayScheduleFragment newInstance(String scheduleName, int dayOrdinal) {
        TwoWeekFormatDayScheduleFragment newFragment = new TwoWeekFormatDayScheduleFragment();
        Bundle args = new Bundle();
        args.putString(SCHEDULE_NAME_BUNDLE_KEY, scheduleName);
        args.putInt(DAY_ORDINAL_BUNDLE_KEY, dayOrdinal);
        newFragment.setArguments(args);
        return newFragment;
    }

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
        Bundle args = getArguments();
        if (args != null) {
            for (int i = 0; i < weeksNames.length; i++) {
                adapter.addFragment(
                        DayScheduleFragment.newInstance(
                                args.getString(SCHEDULE_NAME_BUNDLE_KEY),
                                i + 1,
                                args.getInt(DAY_ORDINAL_BUNDLE_KEY)
                        ),
                        weeksNames[i]
                );
            }
        }
        this.viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
