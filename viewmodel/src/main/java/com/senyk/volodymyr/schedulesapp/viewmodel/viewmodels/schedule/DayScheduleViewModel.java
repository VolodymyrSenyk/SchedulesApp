package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.model.models.dto.DayDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.SingleEventLiveData;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.PairDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseReactiveViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static java.util.stream.Collectors.counting;

public class DayScheduleViewModel extends BaseReactiveViewModel {
    private static final String TAG = "DayScheduleVM";
    private static final int MAX_PAIRS_COUNT = 7;

    private final SchedulesRepository repository;
    private final GenericDtoUiListMapper<PairDto, PairUi> mapper;
    private final PairDtoUiMapper simpleMapper;

    private MutableLiveData<Boolean> isEditMode = new MutableLiveData<>();
    private MutableLiveData<List<PairUi>> pairsList = new MutableLiveData<>();
    private MutableLiveData<Boolean> showMaxCountReachedWarning = new SingleEventLiveData<>();

    public LiveData<Boolean> isEditMode() {
        return this.isEditMode;
    }

    public void setIsEditMode(boolean isEditMode) {
        this.isEditMode.setValue(isEditMode);
    }

    public LiveData<List<PairUi>> getPairsList() {
        return this.pairsList;
    }

    public LiveData<Boolean> needToShowMaxCountReachedWarning() {
        return this.showMaxCountReachedWarning;
    }

    public DayScheduleViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            GenericDtoUiListMapper<PairDto, PairUi> pairDtoUiListMapper,
            PairDtoUiMapper simpleMapper
    ) {
        super(errorsHandler);
        this.repository = schedulesRepository;
        this.mapper = pairDtoUiListMapper;
        this.simpleMapper = simpleMapper;
    }

    public void loadSchedule(String scheduleName, int weekIndex, int dayIndex) {
        this.repository.getScheduleForOneDay(scheduleName, weekIndex + 1, dayIndex + 1)
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
                        pairsList.setValue(pairs);
                        isEditMode.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    public void saveSchedule(String scheduleName, int weekIndex, int dayIndex) {
        List<PairUi> filteredList = new ArrayList<>();
        if (this.pairsList.getValue() != null && !this.pairsList.getValue().isEmpty()) {
            for (PairUi item : this.pairsList.getValue()) {
                if (!item.equals(simpleMapper.convertToUi(PairDto.getBuilder().build()))) {
                    filteredList.add(item);
                }
            }
        }
        DayDto dayToSave = new DayDto(dayIndex + 1, mapper.convertToDto(filteredList));
        this.repository.updateSchedule(scheduleName, weekIndex + 1, dayToSave)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        loadSchedule(scheduleName, weekIndex, dayIndex);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    public void addNewPair() {
        List<PairUi> extendedList = new ArrayList<>();
        if (pairsList.getValue() != null) {
            if (pairsList.getValue().size() > MAX_PAIRS_COUNT) {
                showMaxCountReachedWarning.setValue(true);
                return;
            }
            extendedList.addAll(pairsList.getValue());
        }
        extendedList.add(simpleMapper.convertToUi(PairDto.getBuilder().build()));
        pairsList.setValue(extendedList);
    }

    public void deletePair(PairUi pair) {
        List<PairUi> croppedList = new ArrayList<>();
        if (pairsList.getValue() != null) {
            croppedList.addAll(pairsList.getValue());
        }
        croppedList.remove(pair);
        pairsList.setValue(croppedList);
    }
}
