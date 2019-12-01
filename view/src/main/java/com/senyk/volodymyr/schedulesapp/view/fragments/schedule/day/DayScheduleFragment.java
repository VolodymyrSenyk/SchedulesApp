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
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.WarningClickListener;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.view.listeners.PairsClickListener;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.DayUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule.DayScheduleViewModel;

import java.util.ArrayList;

public class DayScheduleFragment extends BaseFragment implements PairsClickListener, WarningClickListener {
    private static final String SCHEDULE_NAME_BUNDLE_KEY = "Schedule name bundle key";
    private static final String WEEK_ORDINAL_BUNDLE_KEY = "Week ordinal number bundle key";
    private static final String DAY_ORDINAL_BUNDLE_KEY = "Day ordinal number bundle key";

    private DayScheduleViewModel viewModel;

    private FloatingActionButton editSaveButton;
    private ShowPairsListAdapter showPairsListAdapter;
    private EditPairsListAdapter editPairsListAdapter;
    private RecyclerView pairsList;

    private String scheduleName;
    private int weekOrdinal;
    private int dayOrdinal;

    public static DayScheduleFragment newInstance(String scheduleName, int weekOrdinal, int dayOrdinal) {
        DayScheduleFragment newFragment = new DayScheduleFragment();
        Bundle args = new Bundle();
        args.putString(SCHEDULE_NAME_BUNDLE_KEY, scheduleName);
        args.putInt(WEEK_ORDINAL_BUNDLE_KEY, weekOrdinal);
        args.putInt(DAY_ORDINAL_BUNDLE_KEY, dayOrdinal);
        newFragment.setArguments(args);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.viewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(DayScheduleViewModel.class);

        Bundle args = this.getArguments();
        if (args == null) {
            Toast.makeText(requireContext(), "ERROR!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        initNavigationFields(args);
        setViewState(view);
        addObservers();

        viewModel.loadScheduleForOneDay(this.scheduleName, this.weekOrdinal, this.dayOrdinal);
    }

    private void initNavigationFields(Bundle args) {
        this.scheduleName = args.getString(SCHEDULE_NAME_BUNDLE_KEY);
        this.weekOrdinal = args.getInt(WEEK_ORDINAL_BUNDLE_KEY);
        this.dayOrdinal = args.getInt(DAY_ORDINAL_BUNDLE_KEY);
    }

    private void setViewState(View view) {
        this.editSaveButton = view.findViewById(R.id.edit_save_button);

        RecyclerView pairsList = view.findViewById(R.id.pairs_list);
        pairsList.setHasFixedSize(true);
        pairsList.setNestedScrollingEnabled(false);
        pairsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.pairsList = pairsList;

        this.showPairsListAdapter = new ShowPairsListAdapter(this, new ArrayList<>());
        this.editPairsListAdapter = new EditPairsListAdapter(this);
    }

    private void addObservers() {
        viewModel.message
                .observe(this.getViewLifecycleOwner(), message -> Toast.makeText(
                        requireContext(),
                        message,
                        Toast.LENGTH_LONG
                ).show());

        viewModel.isEditMode()
                .observe(this.getViewLifecycleOwner(), isEditMode -> {
                            if (isEditMode) {
                                setEditMode();
                            } else {
                                setShowMode();
                            }
                        }
                );

        viewModel.getScheduleForOneDay()
                .observe(this.getViewLifecycleOwner(), scheduleForOneDay -> {
                    if (scheduleForOneDay.getWeekOrdinal() == this.weekOrdinal &&
                            scheduleForOneDay.getOrdinal() == this.dayOrdinal) {
                        showPairsListAdapter.setUiItems(scheduleForOneDay.getPairs());
                        editPairsListAdapter.setPairsList(scheduleForOneDay.getPairs());
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
        this.editSaveButton.setOnClickListener(view ->
                viewModel.updateScheduleForOneDay(
                        this.scheduleName,
                        new DayUi(
                                this.dayOrdinal,
                                this.weekOrdinal,
                                this.editPairsListAdapter.getPairsList()
                        )
                )
        );

        this.pairsList.setAdapter(this.editPairsListAdapter);
    }

    @Override
    public void pairAddButtonClicked() {
        this.editPairsListAdapter.addNewPair();
    }

    @Override
    public void pairDeleteButtonClicked(PairUi pair) {
        this.editPairsListAdapter.deletePair(pair);
    }

    @Override
    public void onNeutralButtonClick(String dialogType) {
    }
}
