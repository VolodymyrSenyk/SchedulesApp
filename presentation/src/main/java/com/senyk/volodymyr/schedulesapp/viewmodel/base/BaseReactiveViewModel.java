package com.senyk.volodymyr.schedulesapp.viewmodel.base;

import android.util.Log;

import com.senyk.volodymyr.schedulesapp.view.helper.ErrorsHandler;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseReactiveViewModel extends BaseViewModel {
    protected final CompositeDisposable compositeDisposable;

    public BaseReactiveViewModel(String tag, ErrorsHandler errorsHandler) {
        super(tag, errorsHandler);
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCleared() {
        this.compositeDisposable.dispose();
    }

    protected abstract class MainSingleObserver<T> implements SingleObserver<T> {
        @Override
        public void onSubscribe(Disposable disposable) {
            compositeDisposable.add(disposable);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(viewModelTag, errorsHandler.handle(e));
        }
    }

    protected abstract class MainCompletableObserver implements CompletableObserver {
        @Override
        public void onSubscribe(Disposable disposable) {
            compositeDisposable.add(disposable);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(viewModelTag, errorsHandler.handle(e));
        }
    }
}
