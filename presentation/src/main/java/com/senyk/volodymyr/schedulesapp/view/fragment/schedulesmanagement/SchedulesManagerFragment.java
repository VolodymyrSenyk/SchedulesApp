package com.senyk.volodymyr.schedulesapp.view.fragment.schedulesmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegate.listener.SchedulesClickListener;
import com.senyk.volodymyr.schedulesapp.view.adapter.recyclerview.schedules.SchedulesOutputAdapter;
import com.senyk.volodymyr.schedulesapp.view.dialog.DialogFragmentFactory;
import com.senyk.volodymyr.schedulesapp.view.dialog.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialog.clicklisteners.DialogClickListener;
import com.senyk.volodymyr.schedulesapp.view.fragment.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.viewmodel.schedulesmanagement.SchedulesManagerViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.shared.SchedulesNavigationSharedViewModel;

import java.util.ArrayList;

public class SchedulesManagerFragment extends BaseFragment implements SchedulesClickListener, DialogClickListener {
    private static final int DIALOG_FRAGMENT_REQUEST_CODE = 1;

    private SchedulesManagerViewModel viewModel;
    private SchedulesNavigationSharedViewModel sharedViewModel;

    private SchedulesOutputAdapter schedulesListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shedules_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.viewModel = new ViewModelProvider(this, this.viewModelFactory)
                .get(SchedulesManagerViewModel.class);
        this.sharedViewModel = new ViewModelProvider(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);

        this.viewModel.setSkipSchedulesDataOutput(this.sharedViewModel.isAppInit());

        setView(view);
        addObservers();

        requireActivity().getOnBackPressedDispatcher()
                .addCallback(this.getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        NavHostFragment.findNavController(SchedulesManagerFragment.this)
                                .navigate(SchedulesManagerFragmentDirections.goToSchedule());
                    }
                });

        this.sharedViewModel.setIsLoading(true);
        this.viewModel.loadSchedules();
    }

    private void setView(View view) {
        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.schedules_manager_screen_title);

        view.findViewById(R.id.next_button).setVisibility(View.GONE);

        AppCompatImageButton cancelButton = view.findViewById(R.id.back_button);
        cancelButton.setImageResource(R.drawable.ic_back);
        cancelButton.setOnClickListener(view1 -> NavHostFragment.findNavController(this)
                .navigate(SchedulesManagerFragmentDirections.goToSchedule()));

        this.schedulesListAdapter = new SchedulesOutputAdapter(this, new ArrayList<>());
        RecyclerView dataList = view.findViewById(R.id.all_schedules_list);
        dataList.setHasFixedSize(true);
        dataList.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        dataList.setLayoutManager(layoutManager);
        dataList.setAdapter(this.schedulesListAdapter);

        FloatingActionButton addButton = view.findViewById(R.id.add_new_schedule_button);
        addButton.setOnClickListener(view12 -> NavHostFragment.findNavController(this)
                .navigate(SchedulesManagerFragmentDirections.createNewSchedule(false)));
    }

    private void addObservers() {
        this.viewModel.isNoSchedules()
                .observe(this.getViewLifecycleOwner(), isNoSchedules ->
                        NavHostFragment.findNavController(SchedulesManagerFragment.this)
                                .navigate(SchedulesManagerFragmentDirections.createNewSchedule(true)));

        this.viewModel.getCurrentScheduleName()
                .observe(this.getViewLifecycleOwner(), scheduleName ->
                        this.sharedViewModel.setCurrentScheduleName(scheduleName));

        this.viewModel.getSchedulesOutputList()
                .observe(requireActivity(), schedules ->
                        this.schedulesListAdapter.setUiItems(schedules));

        this.viewModel.goToSchedule()
                .observe(this.getViewLifecycleOwner(), currentScheduleName ->
                        NavHostFragment.findNavController(SchedulesManagerFragment.this)
                                .navigate(SchedulesManagerFragmentDirections.goToSchedule()));

        this.viewModel.needToShowConfirmSwapDialog()
                .observe(this.getViewLifecycleOwner(), needToShow ->
                        showSchedulesSwapDialog());

        this.viewModel.getMessage()
                .observe(this.getViewLifecycleOwner(), message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());
    }

    @Override
    public void scheduleClicked(String clickedScheduleName) {
        this.viewModel.scheduleSelected(clickedScheduleName);
    }

    @Override
    public void scheduleDeleteClicked(String clickedScheduleName) {
        showDeleteScheduleDialog(clickedScheduleName);
    }

    private void showSchedulesSwapDialog() {
        DialogFragmentFactory dialogFactory = DialogFragmentFactory.newInstance(DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG);
        dialogFactory.setTargetFragment(this, DIALOG_FRAGMENT_REQUEST_CODE);
        dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG);
    }

    private void showDeleteScheduleDialog(String clickedScheduleName) {
        DialogFragmentFactory dialogFactory =
                DialogFragmentFactory.newInstance(DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG, clickedScheduleName);
        dialogFactory.setTargetFragment(this, DIALOG_FRAGMENT_REQUEST_CODE);
        dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG);
    }

    @Override
    public void onPositiveButtonClick(String dialogType, String additionalData) {
        switch (dialogType) {
            case DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG:
                this.viewModel.swapSchedules();
                break;
            case DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG:
                this.viewModel.deleteSchedule(additionalData);
                break;
        }
    }

    @Override
    public void onNegativeButtonClick(String dialogType, String additionalData) {
    }
}
