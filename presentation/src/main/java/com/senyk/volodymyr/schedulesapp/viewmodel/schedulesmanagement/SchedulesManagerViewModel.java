package com.senyk.volodymyr.schedulesapp.viewmodel.schedulesmanagement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.domain.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.domain.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.view.helper.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.view.helper.livedata.SingleEventLiveData;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist.SchedulesDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.ScheduleUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.base.BaseReactiveViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulesManagerViewModel extends BaseReactiveViewModel {
    private static final String TAG = "SchedulesManagerVM";

    private final SchedulesRepository schedulesRepository;
    private final UserSettingsRepository userSettingsRepository;

    private final SchedulesDtoUiListMapper schedulesMapper;

    private boolean skipSchedulesDataOutput;
    private MutableLiveData<String> currentScheduleName = new MutableLiveData<>();
    private MutableLiveData<List<ScheduleUi>> schedulesOutputList = new MutableLiveData<>();
    private MutableLiveData<Boolean> goToSchedule = new SingleEventLiveData<>();
    private MutableLiveData<Boolean> isNoSchedules = new SingleEventLiveData<>();
    private MutableLiveData<Boolean> showConfirmSwapDialog = new SingleEventLiveData<>();
    private String selectedSchedule;

    public SchedulesManagerViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            UserSettingsRepository userSettingsRepository,
            SchedulesDtoUiListMapper scheduleDtoUiListMapper) {
        super(TAG, errorsHandler);
        this.schedulesRepository = schedulesRepository;
        this.userSettingsRepository = userSettingsRepository;
        this.schedulesMapper = scheduleDtoUiListMapper;
    }

    public void setSkipSchedulesDataOutput(boolean skipSchedulesDataOutput) {
        this.skipSchedulesDataOutput = skipSchedulesDataOutput;
    }

    public LiveData<List<ScheduleUi>> getSchedulesOutputList() {
        return this.schedulesOutputList;
    }

    public LiveData<Boolean> goToSchedule() {
        return this.goToSchedule;
    }

    public LiveData<Boolean> isNoSchedules() {
        return this.isNoSchedules;
    }

    public LiveData<Boolean> needToShowConfirmSwapDialog() {
        return this.showConfirmSwapDialog;
    }

    public LiveData<String> getCurrentScheduleName() {
        return this.currentScheduleName;
    }

    public void loadSchedules() {
        this.schedulesRepository.getSchedulesList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<ScheduleDto>>() {
                    @Override
                    public void onSuccess(List<ScheduleDto> schedules) {
                        if (schedules.isEmpty()) {
                            isNoSchedules.setValue(true);
                        } else {
                            getCurrentSchedule(schedules);
                        }
                    }
                });
    }

    public void scheduleSelected(String selectedScheduleName) {
        if (this.currentScheduleName.getValue() != null && !selectedScheduleName.equals(this.currentScheduleName.getValue())) {
            this.selectedSchedule = selectedScheduleName;
            this.showConfirmSwapDialog.setValue(true);
        }
    }

    public void swapSchedules() {
        this.skipSchedulesDataOutput = true;
        setNewCurrentSchedule(this.selectedSchedule);
    }

    public void deleteSchedule(String scheduleName) {
        if (scheduleName.equals(this.currentScheduleName.getValue())) {
            this.schedulesRepository.deleteSchedule(scheduleName)
                    .andThen(this.userSettingsRepository.setCurrentSchedule(""))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MainCompletableObserver() {
                        @Override
                        public void onComplete() {
                            loadSchedules();
                        }
                    });
        } else {
            this.schedulesRepository.deleteSchedule(scheduleName)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MainCompletableObserver() {
                        @Override
                        public void onComplete() {
                            loadSchedules();
                        }
                    });
        }
    }

    private void getCurrentSchedule(List<ScheduleDto> schedulesList) {
        this.userSettingsRepository.getCurrentSchedule()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<String>() {
                    @Override
                    public void onSuccess(String currentScheduleName) {
                        if (currentScheduleName.equals("")) {
                            setNewCurrentSchedule(schedulesList.get(0).getName());
                        } else {
                            SchedulesManagerViewModel.this.currentScheduleName.setValue(currentScheduleName);
                            if (skipSchedulesDataOutput) {
                                goToSchedule.setValue(true);
                            } else {
                                schedulesOutputList.setValue(schedulesMapper.convertToUi(schedulesList, currentScheduleName));
                            }
                        }
                    }
                });
    }

    private void setNewCurrentSchedule(String newCurrentScheduleName) {
        this.userSettingsRepository.setCurrentSchedule(newCurrentScheduleName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        loadSchedules();
                    }
                });
    }
}
