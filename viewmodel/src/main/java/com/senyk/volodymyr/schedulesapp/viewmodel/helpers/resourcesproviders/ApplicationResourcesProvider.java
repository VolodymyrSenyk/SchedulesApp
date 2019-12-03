package com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders;

import android.content.Context;

import com.senyk.volodymyr.schedulesapp.viewmodel.R;

public class ApplicationResourcesProvider {
    private final Context context;

    public ApplicationResourcesProvider(Context context) {
        this.context = context;
    }

    public String getApplicationErrorMessage(String errorMessage) {
        return this.context.getString(R.string.application_error_message, errorMessage);
    }
}
