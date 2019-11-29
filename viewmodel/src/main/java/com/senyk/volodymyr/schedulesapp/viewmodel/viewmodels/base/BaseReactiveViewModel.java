package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseReactiveViewModel extends BaseViewModel {
    protected final CompositeDisposable compositeDisposable;

    public BaseReactiveViewModel(ErrorsHandler errorsHandler) {
        super(errorsHandler);
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCleared() {
        this.compositeDisposable.dispose();
    }
}
