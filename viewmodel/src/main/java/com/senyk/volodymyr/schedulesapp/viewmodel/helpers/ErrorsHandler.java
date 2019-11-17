package com.senyk.volodymyr.schedulesapp.viewmodel.helpers;

public class ErrorsHandler {
    private ResourcesProvider resourcesProvider;

    public ErrorsHandler(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    public String handle(Throwable e) {
        return resourcesProvider.getErrorMessage(e.getMessage());
    }
}
