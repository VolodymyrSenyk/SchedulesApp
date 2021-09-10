package com.senyk.volodymyr.schedulesapp.view.helper;

import com.senyk.volodymyr.schedulesapp.view.helper.resourcesprovider.ApplicationResourcesProvider;

public class ErrorsHandler {
    private final ApplicationResourcesProvider resourcesProvider;

    public ErrorsHandler(ApplicationResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    public String handle(Throwable e) {
        return this.resourcesProvider.getApplicationErrorMessage(e.getMessage());
    }
}
