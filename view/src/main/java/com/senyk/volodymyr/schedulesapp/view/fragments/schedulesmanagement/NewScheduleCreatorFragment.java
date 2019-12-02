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

import com.google.android.material.button.MaterialButton;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules.ScheduleCreationAdapter;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentFactory;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.WarningClickListener;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.view.listeners.NewScheduleNameFieldListener;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.NewScheduleCreatorViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

import java.util.ArrayList;

public class NewScheduleCreatorFragment extends BaseFragment implements NewScheduleNameFieldListener, WarningClickListener {
    private static final int DIALOG_FRAGMENT_REQUEST_CODE = 1;

    private NewScheduleCreatorViewModel viewModel;
    private SchedulesNavigationSharedViewModel sharedViewModel;

    private ScheduleCreationAdapter scheduleCreationAdapter;

    private NewScheduleCreatorFragmentArgs args;

    private MaterialButton saveBottomButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_schedule_creator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = ViewModelProviders.of(this, this.viewModelFactory)
                .get(NewScheduleCreatorViewModel.class);
        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);

        if (this.getArguments() != null) {
            args = NewScheduleCreatorFragmentArgs.fromBundle(this.getArguments());
        }

        setView(view);
        addObservers();

        requireActivity().getOnBackPressedDispatcher()
                .addCallback(this.getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (args.getIsSingleSchedule()) {
                            requireActivity().finish();
                        } else {
                            NavHostFragment.findNavController(NewScheduleCreatorFragment.this)
                                    .popBackStack();
                        }
                    }
                });

        viewModel.loadInputFields();
    }

    private void setView(View view) {
        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.new_schedule_creator_screen_title);

        view.findViewById(R.id.next_button).setVisibility(View.GONE);

        this.saveBottomButton = view.findViewById(R.id.confirm_creation_bottom_button);
        disableSaveButtons();

        AppCompatImageButton cancelButton = view.findViewById(R.id.back_button);
        cancelButton.setImageResource(R.drawable.ic_cancel);
        cancelButton.setOnClickListener(view1 -> {
            if (args.getIsSingleSchedule()) {
                requireActivity().finish();
            } else {
                NavHostFragment.findNavController(NewScheduleCreatorFragment.this)
                        .popBackStack();
            }
        });

        scheduleCreationAdapter = new ScheduleCreationAdapter(this, new ArrayList<>());
        RecyclerView dataList = view.findViewById(R.id.new_schedule_input_field_list);
        dataList.setHasFixedSize(true);
        dataList.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        dataList.setLayoutManager(layoutManager);
        dataList.setAdapter(scheduleCreationAdapter);
    }

    private void addObservers() {
        viewModel.getInputFields()
                .observe(this.getViewLifecycleOwner(), inputFields -> scheduleCreationAdapter.setItems(inputFields));

        viewModel.getCurrentScheduleName()
                .observe(this.getViewLifecycleOwner(), scheduleName ->
                        sharedViewModel.setCurrentScheduleName(scheduleName));

        viewModel.needToShowSchedule()
                .observe(this.getViewLifecycleOwner(), currentScheduleName ->
                        NavHostFragment.findNavController(NewScheduleCreatorFragment.this)
                                .navigate(NewScheduleCreatorFragmentDirections.goToNewSchedule())
                );

        viewModel.needToShowScheduleExistsWarning()
                .observe(this.getViewLifecycleOwner(), needToShow -> {
                    if (needToShow) {
                        showScheduleExistsDialog();
                    }
                });

        viewModel.message
                .observe(this.getViewLifecycleOwner(), message -> Toast.makeText(
                        requireContext(),
                        message,
                        Toast.LENGTH_LONG
                ).show());
    }

    private void disableSaveButtons() {
        this.saveBottomButton.setEnabled(false);
        this.saveBottomButton.setOnClickListener(null);
    }

    private void enableSaveButtons() {
        this.saveBottomButton.setEnabled(true);
        this.saveBottomButton.setOnClickListener(view -> viewModel.saveNewSchedule());
    }

    private void showScheduleExistsDialog() {
        DialogFragmentFactory dialogFactory = DialogFragmentFactory.newInstance(
                DialogFragmentsTypes.SCHEDULE_ALREADY_EXISTS_WARNING
        );
        dialogFactory.setTargetFragment(NewScheduleCreatorFragment.this, DIALOG_FRAGMENT_REQUEST_CODE);
        dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.SCHEDULE_ALREADY_EXISTS_WARNING);
    }

    @Override
    public void scheduleNameFieldFilled() {
        enableSaveButtons();
    }

    @Override
    public void scheduleNameFieldCleared() {
        disableSaveButtons();
    }

    @Override
    public void onNeutralButtonClick(String dialogType) {
    }
}
