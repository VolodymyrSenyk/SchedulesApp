package com.senyk.volodymyr.schedulesapp.view.fragments.schedule.week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapters.viewpager.SimpleTabAdapter;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.view.fragments.schedule.day.DayScheduleFragment;
import com.senyk.volodymyr.schedulesapp.view.fragments.schedule.twoweekformatday.TwoWeekFormatDayScheduleFragment;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule.WeekViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

public class WeekScheduleFragment extends BaseFragment {
    private SchedulesNavigationSharedViewModel sharedViewModel;
    private WeekViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    private SimpleTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);
        this.sharedViewModel.setAppInitFinished();
        this.viewModel = ViewModelProviders.of(this, this.viewModelFactory)
                .get(WeekViewModel.class);
        this.viewModel.loadScheduleData(sharedViewModel.getCurrentScheduleName());

        setView(view);
        addObservers();

        requireActivity().getOnBackPressedDispatcher()
                .addCallback(this.getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
    }

    private void setView(View view) {
        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.main_screen_title);
        view.findViewById(R.id.back_button).setVisibility(View.GONE);
        view.findViewById(R.id.next_button).setVisibility(View.GONE);
        this.viewPager = view.findViewById(R.id.week_pager);
        this.tabLayout = view.findViewById(R.id.week_tabs);
    }

    private void addObservers() {
        this.viewModel.getScheduleData()
                .observe(this.getViewLifecycleOwner(), schedule -> {
                    adapter = new SimpleTabAdapter(requireActivity().getSupportFragmentManager());
                    String[] daysOfTheWeek = getResources().getStringArray(R.array.days_of_the_week);
                    int daysCount =
                            schedule.isSaturdayWorking() ? ScheduleDtoUiMapper.WEEK_LENGTH_WITH_SAT : ScheduleDtoUiMapper.NORMAL_WEEK_LENGTH;
                    for (int i = 0; i < daysCount; i++) {
                        if (schedule.isNumDenomSystem()) {
                            adapter.addFragment(
                                    TwoWeekFormatDayScheduleFragment.newInstance(
                                            schedule.getName(),
                                            i + 1
                                    ),
                                    daysOfTheWeek[i]
                            );
                        } else {
                            adapter.addFragment(
                                    DayScheduleFragment.newInstance(
                                            schedule.getName(),
                                            1,
                                            i + 1
                                    ),
                                    daysOfTheWeek[i]
                            );
                        }
                    }
                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager);
                });

        this.viewModel.message
                .observe(this.getViewLifecycleOwner(), message -> Toast.makeText(
                        requireContext(),
                        message,
                        Toast.LENGTH_LONG
                ).show());
    }
}
