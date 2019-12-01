package com.senyk.volodymyr.schedulesapp.view.fragments.schedule.day;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs.EditPairsListAdapter;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs.ShowPairsListAdapter;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentFactory;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.WarningClickListener;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.view.listeners.PairsClickListener;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule.DayScheduleViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

import java.util.ArrayList;

public class DayScheduleFragment extends BaseFragment implements PairsClickListener, WarningClickListener {
    private static final int DIALOG_FRAGMENT_REQUEST_CODE = 1;

    private SchedulesNavigationSharedViewModel sharedViewModel;
    private DayScheduleViewModel viewModel;

    private FloatingActionButton editSaveButton;
    private ShowPairsListAdapter showPairsListAdapter;
    private EditPairsListAdapter editPairsListAdapter;
    private RecyclerView pairsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);
        this.viewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(DayScheduleViewModel.class);

        setView(view);
        addInitObservers();
    }

    private void setView(View view) {
        this.editSaveButton = view.findViewById(R.id.edit_save_button);

        this.pairsList = view.findViewById(R.id.pairs_list);
        this.pairsList.setHasFixedSize(true);
        this.pairsList.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        this.pairsList.setLayoutManager(layoutManager);

        this.showPairsListAdapter = new ShowPairsListAdapter(this, new ArrayList<>());
        this.editPairsListAdapter = new EditPairsListAdapter(this, new ArrayList<>());
    }

    private void addInitObservers() {
        sharedViewModel.getCurrentScheduleIndexes()
                .observe(this.getViewLifecycleOwner(), indexes -> {
                    addObservers();
                    viewModel.setIsEditMode(false);
                    viewModel.loadSchedule(
                            sharedViewModel.getCurrentScheduleName(),
                            indexes.first,
                            indexes.second
                    );
                });

        viewModel.message
                .observe(this.getViewLifecycleOwner(), message -> Toast.makeText(
                        requireContext(),
                        message,
                        Toast.LENGTH_LONG
                ).show());
    }

    private void addObservers() {
        viewModel.getPairsList()
                .observe(this.getViewLifecycleOwner(), pairs -> {
                    showPairsListAdapter.setUiItems(pairs);
                    editPairsListAdapter.setUiItems(pairs);
                });

        viewModel.isEditMode()
                .observe(this.getViewLifecycleOwner(), isEditMode -> {
                    if (isEditMode) {
                        setEditMode();
                    } else {
                        setShowMode();
                    }
                });

        viewModel.needToShowMaxCountReachedWarning()
                .observe(this.getViewLifecycleOwner(), needToShow -> {
                    if (needToShow) {
                        showMaxPairsCountReachedWarning();
                    }
                });
    }

    private void setShowMode() {
        this.editSaveButton.setImageResource(R.drawable.ic_edit);
        this.editSaveButton.setOnClickListener(view -> viewModel.setIsEditMode(true));

        this.pairsList.setAdapter(this.showPairsListAdapter);
    }

    private void setEditMode() {
        this.editSaveButton.setImageResource(R.drawable.ic_save);
        this.editSaveButton.setOnClickListener(view -> {
            if (sharedViewModel.getCurrentScheduleIndexes().getValue() != null) {
                viewModel.saveSchedule(
                        sharedViewModel.getCurrentScheduleName(),
                        sharedViewModel.getCurrentScheduleIndexes().getValue().first,
                        sharedViewModel.getCurrentScheduleIndexes().getValue().second
                );
            }
        });

        this.pairsList.setAdapter(this.editPairsListAdapter);
    }

    private void showMaxPairsCountReachedWarning() {
        DialogFragmentFactory dialogFactory = DialogFragmentFactory.newInstance(DialogFragmentsTypes.MAX_COUNT_OF_PAIRS_REACHED_WARNING);
        dialogFactory.setTargetFragment(this, DIALOG_FRAGMENT_REQUEST_CODE);
        dialogFactory.show(requireFragmentManager(), DialogFragmentsTypes.MAX_COUNT_OF_PAIRS_REACHED_WARNING);
    }

    @Override
    public void pairAddButtonClicked() {
        viewModel.addNewPair();
    }

    @Override
    public void pairDeleteButtonClicked(PairUi pair) {
        viewModel.deletePair(pair);
    }

    @Override
    public void onNeutralButtonClick(String dialogType) {
    }
}
