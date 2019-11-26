package com.senyk.volodymyr.schedulesapp.view.fragments.schedule.workweek;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesAppSharedViewModel;

public class WorkWeekScheduleFragment extends BaseFragment {
    private SchedulesAppSharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_days_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesAppSharedViewModel.class);
        sharedViewModel.setAppInitFinished();
    }
}
