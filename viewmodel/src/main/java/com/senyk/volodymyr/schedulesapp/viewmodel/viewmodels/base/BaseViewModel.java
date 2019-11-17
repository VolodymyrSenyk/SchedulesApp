package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base;

import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.SingleEventLiveData;

public abstract class BaseViewModel extends ViewModel {
    protected final ErrorsHandler errorsHandler;

    public SingleEventLiveData<String> message;

    public BaseViewModel(ErrorsHandler errorsHandler) {
        this.errorsHandler = errorsHandler;
    }

}
