package com.senyk.volodymyr.schedulesapp.viewmodel.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.schedulesapp.view.helper.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.view.helper.livedata.SingleEventLiveData;

public abstract class BaseViewModel extends ViewModel {
    protected final String viewModelTag;
    protected final ErrorsHandler errorsHandler;

    protected SingleEventLiveData<String> message = new SingleEventLiveData<>();

    public BaseViewModel(String tag, ErrorsHandler errorsHandler) {
        this.viewModelTag = tag;
        this.errorsHandler = errorsHandler;
    }

    public LiveData<String> getMessage() {
        return this.message;
    }
}
