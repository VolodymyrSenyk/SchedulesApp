package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.SingleEventLiveData;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields.ScheduleIsNumDenomSystemCheckField;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields.ScheduleIsSaturdayWorkingCheckField;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields.ScheduleNameInputField;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseReactiveViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewScheduleCreatorViewModel extends BaseReactiveViewModel {
    private static final String TAG = "NewScheduleCreatorVM";

    private final SchedulesRepository schedulesRepository;
    private final UserSettingsRepository userSettingsRepository;
    private final ScheduleDtoUiMapper scheduleMapper;

    private List<String> existSchedules;
    private ScheduleUi newSchedule = new ScheduleUi();
    private ScheduleNameInputField nameField = new ScheduleNameInputField();
    private ScheduleIsSaturdayWorkingCheckField isSatWorkingField = new ScheduleIsSaturdayWorkingCheckField();
    private ScheduleIsNumDenomSystemCheckField isNumDenomSystemField = new ScheduleIsNumDenomSystemCheckField();

    private MutableLiveData<String> currentScheduleName = new MutableLiveData<>();
    private MutableLiveData<List<PrintableOnTheList>> inputFields = new MutableLiveData<>();
    private MutableLiveData<Boolean> goToSchedule = new SingleEventLiveData<>();
    private MutableLiveData<Boolean> showScheduleExistsWarning = new SingleEventLiveData<>();

    public LiveData<String> getCurrentScheduleName() {
        return this.currentScheduleName;
    }

    public LiveData<List<PrintableOnTheList>> getInputFields() {
        return this.inputFields;
    }

    public LiveData<Boolean> needToShowSchedule() {
        return this.goToSchedule;
    }

    public LiveData<Boolean> needToShowScheduleExistsWarning() {
        return this.showScheduleExistsWarning;
    }

    public NewScheduleCreatorViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository repository,
            UserSettingsRepository userSettingsRepository,
            ScheduleDtoUiMapper scheduleMapper) {
        super(errorsHandler);
        this.schedulesRepository = repository;
        this.userSettingsRepository = userSettingsRepository;
        this.scheduleMapper = scheduleMapper;
    }

    public void loadInputFields() {
        List<PrintableOnTheList> fields = new ArrayList<>(3);
        fields.add(nameField);
        fields.add(isSatWorkingField);
        fields.add(isNumDenomSystemField);
        inputFields.setValue(fields);
        getExistSchedules();
    }

    public void saveNewSchedule() {
        newSchedule.setName(nameField.getName());
        if (existSchedules.contains(newSchedule.getName())) {
            showScheduleExistsWarning.setValue(true);
            return;
        }
        newSchedule.setSaturdayWorking(isSatWorkingField.isChecked());
        newSchedule.setNumDenomSystem(isNumDenomSystemField.isChecked());
        schedulesRepository.createNewSchedule(scheduleMapper.convertToDto(newSchedule))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        setCurrentSchedule();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    private void setCurrentSchedule() {
        userSettingsRepository.setSchedule(newSchedule.getName())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        setNewCurrentSchedule(newSchedule.getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    private void getExistSchedules() {
        schedulesRepository.getSchedulesList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ScheduleDto>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(List<ScheduleDto> schedules) {
                        existSchedules = new ArrayList<>();
                        for (ScheduleDto item : schedules) {
                            existSchedules.add(item.getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

    private void setNewCurrentSchedule(String newCurrentScheduleName) {
        userSettingsRepository.setSchedule(newCurrentScheduleName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        currentScheduleName.setValue(newSchedule.getName());
                        goToSchedule.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, errorsHandler.handle(e));
                    }
                });
    }

}
