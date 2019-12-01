package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.model.models.dto.DayDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.DayUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseReactiveViewModel;

import java.util.Collections;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DayScheduleViewModel extends BaseReactiveViewModel {
    private static final String TAG = "DayScheduleVM";

    private final SchedulesRepository repository;
    private final GenericDtoUiListMapper<PairDto, PairUi> mapper;

    private MutableLiveData<Boolean> isEditMode = new MutableLiveData<>();
    private MutableLiveData<DayUi> scheduleForOneDay = new MutableLiveData<>();

    public LiveData<Boolean> isEditMode() {
        return this.isEditMode;
    }

    public void setIsEditMode(boolean isEditMode) {
        this.isEditMode.setValue(isEditMode);
    }

    public LiveData<DayUi> getScheduleForOneDay() {
        return this.scheduleForOneDay;
    }

    public DayScheduleViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            GenericDtoUiListMapper<PairDto, PairUi> pairDtoUiListMapper
    ) {
        super(errorsHandler);
        this.repository = schedulesRepository;
        this.mapper = pairDtoUiListMapper;
    }

    public void loadScheduleForOneDay(String scheduleName, int weekOrdinal, int dayOrdinal) {
        repository.getScheduleForOneDay(scheduleName, weekOrdinal, dayOrdinal)
                .subscribeOn(Schedulers.newThread())
                .map(mapper::convertToUi)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PairUi>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(List<PairUi> pairs) {
                        Collections.sort(pairs, (pair1, pair2) -> Long.compare(pair1.getTimeInMillis(), pair2.getTimeInMillis()));
                        scheduleForOneDay.setValue(new DayUi(dayOrdinal, weekOrdinal, pairs));
                        isEditMode.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    public void updateScheduleForOneDay(String scheduleName, DayUi day) {
        DayDto dayToSave = new DayDto(day.getOrdinal(), mapper.convertToDto(day.getPairs()));
        repository.updateSchedule(scheduleName, day.getWeekOrdinal(), dayToSave)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        loadScheduleForOneDay(scheduleName, day.getWeekOrdinal(), day.getOrdinal());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }
}
