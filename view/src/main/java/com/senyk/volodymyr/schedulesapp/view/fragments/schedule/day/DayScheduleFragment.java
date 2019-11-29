package com.senyk.volodymyr.schedulesapp.view.fragments.schedule.day;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

public class DayScheduleFragment extends BaseFragment {
    private SchedulesNavigationSharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug_fragment_day_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);

        final TextView debugNavView = view.findViewById(R.id.debug_navigation_view);
        sharedViewModel.getCurrentScheduleIndexes()
                .observe(this.getViewLifecycleOwner(), indexes -> {
                    String[] daysOfTheWeek = getResources().getStringArray(R.array.days_of_the_week);
                    debugNavView.setText(
                            getString(R.string.debug_schedules_navigation,
                                    daysOfTheWeek[indexes.second],
                                    indexes.first + 1)
                    );
                });
    }
}
