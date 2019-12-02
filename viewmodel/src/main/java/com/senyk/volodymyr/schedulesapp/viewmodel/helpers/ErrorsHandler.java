package com.senyk.volodymyr.schedulesapp.viewmodel.helpers;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.ApplicationResourcesProvider;

public class ErrorsHandler {
    private final ApplicationResourcesProvider resourcesProvider;

    public ErrorsHandler(ApplicationResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    public String handle(Throwable e) {
        return this.resourcesProvider.getApplicationErrorMessage(e.getMessage());
    }
}
