package com.senyk.volodymyr.schedulesapp.view.fragment.pairsmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapter.viewpager.SimpleTabAdapter;
import com.senyk.volodymyr.schedulesapp.view.fragment.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.pairsmanagement.StudyWeekViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.shared.SchedulesNavigationSharedViewModel;

public class StudyWeekFragment extends BaseFragment {
    private SchedulesNavigationSharedViewModel sharedViewModel;
    private StudyWeekViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study_week, container, false);
    }

    private SimpleTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View progressBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.sharedViewModel = new ViewModelProvider(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);
        this.sharedViewModel.setAppInitFinished();
        this.viewModel = new ViewModelProvider(this, this.viewModelFactory)
                .get(StudyWeekViewModel.class);

        setView(view);
        addObservers();

        this.viewModel.loadScheduleData(sharedViewModel.getCurrentScheduleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        this.sharedViewModel.saveCurrentDayIndex(this.viewPager.getCurrentItem());
    }

    private void setView(View view) {
        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.main_screen_title);
        view.findViewById(R.id.back_button).setVisibility(View.GONE);
        view.findViewById(R.id.next_button).setVisibility(View.GONE);
        this.viewPager = view.findViewById(R.id.week_pager);
        this.tabLayout = view.findViewById(R.id.week_tabs);
        this.progressBar = view.findViewById(R.id.progressBar);
    }

    private void addObservers() {
        this.viewModel.getScheduleData()
                .observe(this.getViewLifecycleOwner(), schedule -> {
                    this.adapter = new SimpleTabAdapter(requireActivity().getSupportFragmentManager());
                    String[] daysOfTheWeek = getResources().getStringArray(R.array.days_of_the_week);
                    int daysCount = schedule.isSaturdayWorking() ?
                            ScheduleDtoUiMapper.WEEK_LENGTH_WITH_SAT :
                            ScheduleDtoUiMapper.NORMAL_WEEK_LENGTH;
                    for (int i = 0; i < daysCount; i++) {
                        if (schedule.isNumDenomSystem()) {
                            this.adapter.addFragment(
                                    NumDenomScheduleFragment
                                            .newInstance(schedule.getName(), i + 1),
                                    daysOfTheWeek[i]);
                        } else {
                            this.adapter.addFragment(
                                    OneDayScheduleFragment
                                            .newInstance(schedule.getName(), 1, i + 1),
                                    daysOfTheWeek[i]);
                        }
                    }
                    this.viewPager.setAdapter(this.adapter);
                    this.tabLayout.setupWithViewPager(this.viewPager);
                    this.viewPager.setCurrentItem(this.sharedViewModel.getCurrentDayIndex());
                });

        this.sharedViewModel.isLoading()
                .observe(this.getViewLifecycleOwner(), isLoading -> {
                    if (isLoading) {
                        this.progressBar.setVisibility(View.VISIBLE);
                    } else {
                        this.progressBar.setVisibility(View.GONE);
                    }
                });

        this.viewModel.getMessage()
                .observe(this.getViewLifecycleOwner(), message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());
    }
}
