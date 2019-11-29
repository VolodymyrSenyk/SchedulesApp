package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.SchedulesDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseReactiveViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WeekViewModel extends BaseReactiveViewModel {
    private static final String TAG = "WeekVM";

    private final SchedulesRepository schedulesRepository;
    private final SchedulesDtoUiListMapper schedulesDtoUiListMapper;

    private MutableLiveData<ScheduleUi> scheduleData = new MutableLiveData<>();

    public LiveData<ScheduleUi> getScheduleData() {
        return this.scheduleData;
    }

    public WeekViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            SchedulesDtoUiListMapper schedulesDtoUiListMapper) {
        super(errorsHandler);
        this.schedulesRepository = schedulesRepository;
        this.schedulesDtoUiListMapper = schedulesDtoUiListMapper;
    }

    public void loadScheduleData(String scheduleName) {
        this.schedulesRepository.getSchedulesList()
                .subscribeOn(Schedulers.newThread())
                .toObservable()
                .flatMap((Function<List<ScheduleDto>, Observable<ScheduleDto>>) Observable::fromIterable)
                .filter(scheduleDto -> scheduleDto.getName().equals(scheduleName))
                .toList()
                .map(schedulesDtoUiListMapper::convertToUi)
                .map(scheduleUis -> scheduleUis.get(0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ScheduleUi>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(ScheduleUi schedules) {
                        scheduleData.setValue(schedules);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

}
