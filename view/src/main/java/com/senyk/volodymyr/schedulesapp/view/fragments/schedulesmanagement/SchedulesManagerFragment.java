package com.senyk.volodymyr.schedulesapp.view.fragments.schedulesmanagement;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules.SchedulesOutputAdapter;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentFactory;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.DialogClickListener;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.view.listeners.SchedulesClickListener;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.SchedulesManagerViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

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
        this.viewModel = ViewModelProviders.of(this, this.viewModelFactory)
                .get(SchedulesManagerViewModel.class);
        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);

        viewModel.isAppStart = sharedViewModel.isAppInit();

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

        viewModel.loadSchedules();
        sharedViewModel.setIsLoading(true);
    }

    private void setView(View view) {
        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.schedules_manager_screen_title);
        view.findViewById(R.id.next_button).setVisibility(View.GONE);
        AppCompatImageButton cancelButton = view.findViewById(R.id.back_button);
        cancelButton.setImageResource(R.drawable.ic_back);
        cancelButton.setOnClickListener(view1 -> NavHostFragment.findNavController(SchedulesManagerFragment.this)
                .navigate(SchedulesManagerFragmentDirections.goToSchedule()));

        schedulesListAdapter = new SchedulesOutputAdapter(this, new ArrayList<>());
        RecyclerView dataList = view.findViewById(R.id.all_schedules_list);
        dataList.setHasFixedSize(true);
        dataList.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        dataList.setLayoutManager(layoutManager);
        dataList.setAdapter(schedulesListAdapter);

        FloatingActionButton addButton = view.findViewById(R.id.add_new_schedule_button);
        addButton.setOnClickListener(view12 -> NavHostFragment.findNavController(SchedulesManagerFragment.this)
                .navigate(SchedulesManagerFragmentDirections.createNewSchedule(false)));
    }

    private void addObservers() {
        viewModel.isNoSchedules()
                .observe(this.getViewLifecycleOwner(), isNoSchedules -> {
                    if (isNoSchedules != null && isNoSchedules) {
                        NavHostFragment.findNavController(SchedulesManagerFragment.this)
                                .navigate(SchedulesManagerFragmentDirections.createNewSchedule(true));
                    }
                });

        viewModel.getCurrentScheduleName()
                .observe(this.getViewLifecycleOwner(), scheduleName ->
                        sharedViewModel.setCurrentScheduleName(scheduleName));

        viewModel.getSchedulesOutputList()
                .observe(requireActivity(), schedules -> schedulesListAdapter.setUiItems(schedules));

        viewModel.needToShowSchedule()
                .observe(this.getViewLifecycleOwner(), currentScheduleName ->
                        NavHostFragment.findNavController(SchedulesManagerFragment.this)
                                .navigate(SchedulesManagerFragmentDirections.goToSchedule())
                );

        viewModel.needToShowConfirmSwapDialog()
                .observe(this.getViewLifecycleOwner(), needToShow -> {
                    if (needToShow) {
                        showSchedulesSwapDialog();
                    }
                });

        viewModel.message
                .observe(this.getViewLifecycleOwner(), message -> Toast.makeText(
                        requireContext(),
                        message,
                        Toast.LENGTH_LONG
                ).show());
    }

    @Override
    public void scheduleClicked(String clickedScheduleName) {
        viewModel.scheduleSelected(clickedScheduleName);
    }

    @Override
    public void scheduleDeleteClicked(String clickedScheduleName) {
        showDeleteScheduleDialog(clickedScheduleName);
    }

    private void showSchedulesSwapDialog() {
        DialogFragmentFactory dialogFactory = DialogFragmentFactory.newInstance(DialogFragmentsTypes.CURRENT_SCHEDULE_SWAP_DIALOG);
        dialogFactory.setTargetFragment(SchedulesManagerFragment.this, DIALOG_FRAGMENT_REQUEST_CODE);
        dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.CURRENT_SCHEDULE_SWAP_DIALOG);
    }

    private void showDeleteScheduleDialog(String clickedScheduleName) {
        DialogFragmentFactory dialogFactory = DialogFragmentFactory.newInstance(
                DialogFragmentsTypes.SCHEDULE_DELETING_DIALOG,
                clickedScheduleName
        );
        dialogFactory.setTargetFragment(SchedulesManagerFragment.this, DIALOG_FRAGMENT_REQUEST_CODE);
        dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.SCHEDULE_DELETING_DIALOG);
    }

    @Override
    public void onPositiveButtonClick(String dialogType, String additionalData) {
        switch (dialogType) {
            case DialogFragmentsTypes.CURRENT_SCHEDULE_SWAP_DIALOG:
                viewModel.managementFinished();
                break;
            case DialogFragmentsTypes.SCHEDULE_DELETING_DIALOG:
                viewModel.deleteSchedule(additionalData);
                break;
        }

    }

    @Override
    public void onNegativeButtonClick(String dialogType, String additionalData) {
    }
}
