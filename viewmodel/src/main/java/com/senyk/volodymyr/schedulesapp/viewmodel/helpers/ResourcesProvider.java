package com.senyk.volodymyr.schedulesapp.viewmodel.helpers;

import android.content.res.Resources;

public class ResourcesProvider {
    private Resources resources;

    public ResourcesProvider(Resources resources) {
        this.resources = resources;
    }

    public String getErrorMessage(String errorMessage) {
        return errorMessage;
    }
}
