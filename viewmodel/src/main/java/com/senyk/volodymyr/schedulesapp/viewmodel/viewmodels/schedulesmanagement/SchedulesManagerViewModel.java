package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.SingleEventLiveData;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.SchedulesDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseReactiveViewModel;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SchedulesManagerViewModel extends BaseReactiveViewModel {
    private final static String TAG = "SchedulesManagerVM";

    private final SchedulesRepository schedulesRepository;
    private final UserSettingsRepository userSettingsRepository;

    private final SchedulesDtoUiListMapper schedulesMapper;

    public boolean isAppStart;
    private List<ScheduleDto> schedulesList;
    private String selectedSchedule;

    private MutableLiveData<String> currentScheduleName = new MutableLiveData<>();
    private MutableLiveData<List<ScheduleUi>> schedulesOutputList = new MutableLiveData<>();
    private MutableLiveData<Boolean> goToSchedule = new SingleEventLiveData<>();
    private MutableLiveData<Boolean> isNoSchedules = new SingleEventLiveData<>();
    private MutableLiveData<Boolean> showConfirmSwapDialog = new SingleEventLiveData<>();

    public LiveData<List<ScheduleUi>> getSchedulesOutputList() {
        return this.schedulesOutputList;
    }

    public LiveData<Boolean> needToShowSchedule() {
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

    public SchedulesManagerViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            UserSettingsRepository userSettingsRepository,
            SchedulesDtoUiListMapper scheduleDtoUiListMapper) {
        super(errorsHandler);
        this.schedulesRepository = schedulesRepository;
        this.userSettingsRepository = userSettingsRepository;
        this.schedulesMapper = scheduleDtoUiListMapper;
    }

    public void loadSchedules() {
        schedulesRepository.getSchedulesList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ScheduleDto>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(List<ScheduleDto> scheduleDtos) {
                        if (scheduleDtos.isEmpty()) {
                            isNoSchedules.setValue(true);
                        } else {
                            schedulesList = scheduleDtos;
                            getCurrentSchedule();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    public void scheduleSelected(String selectedScheduleName) {
        if (currentScheduleName.getValue() != null) {
            if (!selectedScheduleName.equals(currentScheduleName.getValue())) {
                selectedSchedule = selectedScheduleName;
                showConfirmSwapDialog.setValue(true);
            }
        }
    }

    public void deleteSchedule(String scheduleName) {
        if (scheduleName.equals(this.currentScheduleName.getValue())) {
            schedulesRepository.deleteSchedule(scheduleName)
                    .andThen(userSettingsRepository.setCurrentSchedule(""))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable disposable) {
                            compositeDisposable.add(disposable);
                        }

                        @Override
                        public void onComplete() {
                            loadSchedules();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, errorsHandler.handle(e));
                        }
                    });
        } else {
            schedulesRepository.deleteSchedule(scheduleName)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable disposable) {
                            compositeDisposable.add(disposable);
                        }

                        @Override
                        public void onComplete() {
                            loadSchedules();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, errorsHandler.handle(e));
                        }
                    });
        }
    }

    public void managementFinished() {
        if (this.selectedSchedule != null) {
            setNewCurrentSchedule(this.selectedSchedule);
            currentScheduleName.setValue(this.selectedSchedule);
            goToSchedule.setValue(true);
        }
    }

    private void getCurrentSchedule() {
        userSettingsRepository.getCurrentSchedule()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(String currentScheduleName) {
                        if (currentScheduleName.equals("") && !schedulesList.isEmpty()) {
                            currentScheduleName = schedulesList.get(0).getName();
                            setNewCurrentSchedule(currentScheduleName);
                        }
                        SchedulesManagerViewModel.this.currentScheduleName.setValue(currentScheduleName);
                        if (isAppStart) {
                            goToSchedule.setValue(true);
                            return;
                        }
                        schedulesOutputList.setValue(schedulesMapper.convertToUi(schedulesList, currentScheduleName));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    private void setNewCurrentSchedule(String newCurrentScheduleName) {
        userSettingsRepository.setCurrentSchedule(newCurrentScheduleName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        getCurrentSchedule();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }
}
