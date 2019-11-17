package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement;

import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseReactiveViewModel;

public class SchedulesManagerViewModule extends BaseReactiveViewModel {
    private SchedulesRepository repository;

    public SchedulesManagerViewModule(ErrorsHandler errorsHandler, SchedulesRepository repository) {
        super(errorsHandler);
        this.repository = repository;
    }

}
