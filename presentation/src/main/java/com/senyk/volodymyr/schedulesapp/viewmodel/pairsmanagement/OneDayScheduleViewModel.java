package com.senyk.volodymyr.schedulesapp.viewmodel.pairsmanagement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.domain.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.view.helper.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtoui.DayDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.DayUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.base.BaseReactiveViewModel;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OneDayScheduleViewModel extends BaseReactiveViewModel {
    private static final String TAG = "OneDayScheduleVM";

    private final SchedulesRepository repository;
    private final DayDtoUiMapper mapper;

    private MutableLiveData<Boolean> isEditMode = new MutableLiveData<>();
    private MutableLiveData<DayUi> scheduleForOneDay = new MutableLiveData<>();

    public OneDayScheduleViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            DayDtoUiMapper dayDtoUiListMapper) {
        super(TAG, errorsHandler);
        this.repository = schedulesRepository;
        this.mapper = dayDtoUiListMapper;
    }

    public LiveData<Boolean> isEditMode() {
        return this.isEditMode;
    }

    public void setEditMode() {
        this.isEditMode.setValue(true);
    }

    public void setOutputMode() {
        this.isEditMode.setValue(false);
    }

    public LiveData<DayUi> getScheduleForOneDay() {
        return this.scheduleForOneDay;
    }

    public void loadScheduleForOneDay(String scheduleName, int weekOrdinal, int dayOrdinal) {
        this.repository.getScheduleForOneDay(scheduleName, weekOrdinal, dayOrdinal)
                .subscribeOn(Schedulers.newThread())
                .map(this.mapper::convertToUi)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<DayUi>() {
                    @Override
                    public void onSuccess(DayUi day) {
                        Collections.sort(day.getPairs(), (pair1, pair2) -> Long.compare(pair1.getTimeInMillis(), pair2.getTimeInMillis()));
                        scheduleForOneDay.setValue(new DayUi(day.getOrdinal(), weekOrdinal, day.getPairs()));
                        isEditMode.setValue(false);
                    }
                });
    }

    public void updateScheduleForOneDay(String scheduleName, DayUi day) {
        this.repository.updateSchedule(scheduleName, day.getWeekOrdinal(), mapper.convertToDto(day))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        loadScheduleForOneDay(scheduleName, day.getWeekOrdinal(), day.getOrdinal());
                    }
                });
    }
}
