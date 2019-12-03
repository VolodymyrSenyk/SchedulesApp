package com.senyk.volodymyr.schedulesapp.view.fragments.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.senyk.volodymyr.schedulesapp.view.applications.SchedulesApplication;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.factories.ViewModelFactory;

import javax.inject.Inject;

public class BaseFragment extends Fragment {
    @Inject
    protected ViewModelFactory viewModelFactory;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((SchedulesApplication) requireActivity().getApplication()).getAppComponent().inject(this);
    }
}
