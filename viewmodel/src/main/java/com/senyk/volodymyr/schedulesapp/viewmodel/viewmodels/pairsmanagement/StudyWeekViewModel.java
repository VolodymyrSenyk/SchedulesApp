package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.pairsmanagement;

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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class StudyWeekViewModel extends BaseReactiveViewModel {
    private static final String TAG = "StudyWeekVM";

    private final SchedulesRepository schedulesRepository;
    private final SchedulesDtoUiListMapper schedulesMapper;

    private MutableLiveData<ScheduleUi> scheduleData = new MutableLiveData<>();

    public StudyWeekViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            SchedulesDtoUiListMapper schedulesDtoUiListMapper) {
        super(TAG, errorsHandler);
        this.schedulesRepository = schedulesRepository;
        this.schedulesMapper = schedulesDtoUiListMapper;
    }

    public LiveData<ScheduleUi> getScheduleData() {
        return this.scheduleData;
    }

    public void loadScheduleData(String scheduleName) {
        this.schedulesRepository.getSchedulesList()
                .subscribeOn(Schedulers.newThread())
                .toObservable()
                .flatMap((Function<List<ScheduleDto>, Observable<ScheduleDto>>) Observable::fromIterable)
                .filter(scheduleDto -> scheduleDto.getName().equals(scheduleName))
                .toList()
                .map(this.schedulesMapper::convertToUi)
                .map(scheduleUis -> scheduleUis.get(0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<ScheduleUi>() {
                    @Override
                    public void onSuccess(ScheduleUi schedules) {
                        scheduleData.setValue(schedules);
                    }
                });
    }
}
