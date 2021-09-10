package com.senyk.volodymyr.schedulesapp.viewmodel.schedulesmanagement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule;
import com.senyk.volodymyr.schedulesapp.domain.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.domain.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.view.helper.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.view.helper.livedata.SingleEventLiveData;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.view.entity.datainputfields.ScheduleIsNumDenomSystemCheckField;
import com.senyk.volodymyr.schedulesapp.view.entity.datainputfields.ScheduleIsSaturdayWorkingCheckField;
import com.senyk.volodymyr.schedulesapp.view.entity.datainputfields.ScheduleNameInputField;
import com.senyk.volodymyr.schedulesapp.view.entity.ScheduleUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.base.BaseReactiveViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewScheduleCreatorViewModel extends BaseReactiveViewModel {
    private static final String TAG = "NewScheduleCreatorVM";

    private final SchedulesRepository schedulesRepository;
    private final UserSettingsRepository userSettingsRepository;
    private final ScheduleDtoUiMapper scheduleMapper;

    private List<String> existSchedules;
    private ScheduleNameInputField nameField = new ScheduleNameInputField();
    private ScheduleIsSaturdayWorkingCheckField isSatWorkingField = new ScheduleIsSaturdayWorkingCheckField();
    private ScheduleIsNumDenomSystemCheckField isNumDenomSystemField = new ScheduleIsNumDenomSystemCheckField();

    private MutableLiveData<String> currentScheduleName = new MutableLiveData<>();
    private MutableLiveData<List<PrintableOnTheList>> inputFields = new MutableLiveData<>();
    private MutableLiveData<Boolean> showScheduleExistsWarning = new SingleEventLiveData<>();

    public NewScheduleCreatorViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository repository,
            UserSettingsRepository userSettingsRepository,
            ScheduleDtoUiMapper scheduleMapper) {
        super(TAG, errorsHandler);
        this.schedulesRepository = repository;
        this.userSettingsRepository = userSettingsRepository;
        this.scheduleMapper = scheduleMapper;
    }

    public LiveData<String> getCurrentScheduleName() {
        return this.currentScheduleName;
    }

    public LiveData<List<PrintableOnTheList>> getInputFields() {
        return this.inputFields;
    }

    public LiveData<Boolean> needToShowScheduleExistsWarning() {
        return this.showScheduleExistsWarning;
    }

    public void loadInputFields() {
        List<PrintableOnTheList> fields = new ArrayList<>(3);
        fields.add(this.nameField);
        fields.add(this.isSatWorkingField);
        fields.add(this.isNumDenomSystemField);
        this.inputFields.setValue(fields);
        getExistSchedules();
    }

    public void saveNewSchedule() {
        if (this.existSchedules.contains(this.nameField.getName())) {
            this.showScheduleExistsWarning.setValue(true);
            return;
        }

        ScheduleUi newSchedule = new ScheduleUi(
                this.nameField.getName(),
                this.isSatWorkingField.isChecked(),
                this.isNumDenomSystemField.isChecked());

        this.schedulesRepository.createNewSchedule(scheduleMapper.convertToDto(newSchedule))
                .andThen(userSettingsRepository.setCurrentSchedule(newSchedule.getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        currentScheduleName.setValue(newSchedule.getName());
                    }
                });
    }

    private void getExistSchedules() {
        this.schedulesRepository.getSchedulesList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<Schedule>>() {
                    @Override
                    public void onSuccess(List<Schedule> schedules) {
                        existSchedules = new ArrayList<>();
                        for (Schedule item : schedules) {
                            existSchedules.add(item.getName());
                        }
                    }
                });
    }
}
