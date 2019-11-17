package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule;

import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseReactiveViewModel;

public class DayScheduleViewModel extends BaseReactiveViewModel {
    private SchedulesRepository repository;

    public DayScheduleViewModel(ErrorsHandler errorsHandler, SchedulesRepository repository) {
        super(errorsHandler);
        this.repository = repository;
    }

}
