package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.navigation;

import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseViewModel;

public class RedirectViewModule extends BaseViewModel {
    private UserSettingsRepository repository;

    public RedirectViewModule(ErrorsHandler errorsHandler, UserSettingsRepository repository) {
        super(errorsHandler);
        this.repository = repository;
    }

}
