package com.senyk.volodymyr.schedulesapp.view.fragments.pairsmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.listeners.PairsClickListener;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs.EditPairsListAdapter;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs.ShowPairsListAdapter;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.WarningClickListener;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.PairsMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.PairDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.DayUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.pairsmanagement.OneDayScheduleViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

public class OneDayScheduleFragment extends BaseFragment implements PairsClickListener, WarningClickListener {
    private static final String SCHEDULE_NAME_BUNDLE_KEY = "Schedule name bundle key";
    private static final String WEEK_ORDINAL_BUNDLE_KEY = "Week ordinal number bundle key";
    private static final String DAY_ORDINAL_BUNDLE_KEY = "Day ordinal number bundle key";

    private SchedulesNavigationSharedViewModel sharedViewModel;
    private OneDayScheduleViewModel viewModel;

    private FloatingActionButton editSaveButton;
    private ShowPairsListAdapter showPairsListAdapter;
    private EditPairsListAdapter editPairsListAdapter;
    private RecyclerView pairsList;

    private String scheduleName;
    private int weekOrdinal;
    private int dayOrdinal;

    public static OneDayScheduleFragment newInstance(String scheduleName, int weekOrdinal, int dayOrdinal) {
        OneDayScheduleFragment newFragment = new OneDayScheduleFragment();
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
        return inflater.inflate(R.layout.fragment_one_day_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.sharedViewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(SchedulesNavigationSharedViewModel.class);
        this.viewModel = ViewModelProviders.of(requireActivity(), this.viewModelFactory)
                .get(OneDayScheduleViewModel.class);

        Bundle args = this.getArguments();
        if (args == null) {
            Toast.makeText(requireContext(), "ERROR!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        initNavigationFields(args);
        setViewState(view);
        addObservers();

        requireActivity().getOnBackPressedDispatcher()
                .addCallback(this.getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (viewModel.isEditMode().getValue() != null && viewModel.isEditMode().getValue()) {
                            viewModel.setOutputMode();
                        } else {
                            requireActivity().finish();
                        }
                    }
                });

        this.viewModel.loadScheduleForOneDay(this.scheduleName, this.weekOrdinal, this.dayOrdinal);
    }

    private void initNavigationFields(Bundle args) {
        this.scheduleName = args.getString(SCHEDULE_NAME_BUNDLE_KEY);
        this.weekOrdinal = args.getInt(WEEK_ORDINAL_BUNDLE_KEY);
        this.dayOrdinal = args.getInt(DAY_ORDINAL_BUNDLE_KEY);
    }

    private void setViewState(View view) {
        this.editSaveButton = view.findViewById(R.id.edit_save_button);

        RecyclerView pairsListView = view.findViewById(R.id.pairs_list);
        pairsListView.setHasFixedSize(true);
        pairsListView.setNestedScrollingEnabled(false);
        pairsListView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.pairsList = pairsListView;

        this.showPairsListAdapter = new ShowPairsListAdapter(this);
        this.editPairsListAdapter = new EditPairsListAdapter(
                this,
                new PairDtoUiMapper(new PairsMappingResourcesProvider(requireContext())));
    }

    private void addObservers() {
        this.viewModel.getMessage()
                .observe(this.getViewLifecycleOwner(), message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());

        this.viewModel.isEditMode()
                .observe(this.getViewLifecycleOwner(), isEditMode -> {
                            if (isEditMode) {
                                setEditMode();
                            } else {
                                setShowMode();
                            }
                        }
                );

        this.viewModel.getScheduleForOneDay()
                .observe(this.getViewLifecycleOwner(), scheduleForOneDay -> {
                    if (scheduleForOneDay.getWeekOrdinal() == this.weekOrdinal &&
                            scheduleForOneDay.getOrdinal() == this.dayOrdinal) {
                        this.showPairsListAdapter.setUiItems(scheduleForOneDay.getPairs());
                        this.editPairsListAdapter.setPairsList(scheduleForOneDay.getPairs());
                        this.sharedViewModel.setIsLoading(false);
                    }
                });
    }

    private void setShowMode() {
        this.editSaveButton.setImageResource(R.drawable.ic_edit);
        this.editSaveButton.setOnClickListener(view -> viewModel.setEditMode());

        this.pairsList.setAdapter(this.showPairsListAdapter);
    }

    private void setEditMode() {
        this.editSaveButton.setImageResource(R.drawable.ic_save);
        this.editSaveButton.setOnClickListener(view ->
                viewModel.updateScheduleForOneDay(
                        this.scheduleName,
                        new DayUi(this.dayOrdinal, this.weekOrdinal, this.editPairsListAdapter.getPairsList())));

        this.pairsList.setAdapter(this.editPairsListAdapter);
    }

    @Override
    public void pairAddButtonClicked() {
        this.editPairsListAdapter.addNewPair();
        this.pairsList.smoothScrollToPosition(this.editPairsListAdapter.getItemCount());
    }

    @Override
    public void pairDeleteButtonClicked(PairUi pair) {
        this.editPairsListAdapter.deletePair(pair);
    }

    @Override
    public void onNeutralButtonClick(String dialogType) {
    }
}
