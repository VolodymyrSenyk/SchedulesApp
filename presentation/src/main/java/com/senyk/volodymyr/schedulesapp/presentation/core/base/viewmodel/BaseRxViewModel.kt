package com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel

import androidx.annotation.CallSuper
import com.senyk.volodymyr.schedulesapp.di.annotation.qualifier.execution.Background
import com.senyk.volodymyr.schedulesapp.di.annotation.qualifier.execution.Foreground
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

abstract class BaseRxViewModel constructor(
    @Background private val backgroundScheduler: Scheduler = Schedulers.computation(),
    @Foreground private val foregroundScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BaseViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun <T : Any> subscribe(upstream: Observable<T>, onNext: (T) -> Unit = {}) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribeBy(onError = this::onError, onNext = onNext)
            .apply(this::addDisposable)
    }

    protected fun <T : Any> subscribe(upstream: Single<T>, onSuccess: (T) -> Unit) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribeBy(this::onError, onSuccess)
            .apply(this::addDisposable)
    }

    protected fun subscribe(upstream: Completable, onComplete: () -> Unit = {}) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribeBy(this::onError, onComplete)
            .apply(this::addDisposable)
    }

    protected fun <T : Any> subscribe(
        upstream: Maybe<T>,
        onSuccess: (T) -> Unit,
        onComplete: () -> Unit
    ) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribeBy(this::onError, onSuccess = onSuccess, onComplete = onComplete)
            .apply(this::addDisposable)
    }

    protected fun <T : Any> subscribeWithProgress(
        upstream: Observable<T>,
        onNext: (T) -> Unit,
        showProgress: (Disposable) -> Unit = { showProgress() },
        hideProgress: () -> Unit = this::hideProgress
    ) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .doOnSubscribe(showProgress)
            .doAfterTerminate(hideProgress)
            .subscribeBy(onError = this::onError, onNext = onNext)
            .apply(this::addDisposable)
    }

    protected fun <T : Any> subscribeWithProgress(
        upstream: Single<T>,
        onSuccess: (T) -> Unit,
        showProgress: (Disposable) -> Unit = { showProgress() },
        hideProgress: () -> Unit = this::hideProgress
    ) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .doOnSubscribe(showProgress)
            .doAfterTerminate(hideProgress)
            .subscribeBy(this::onError, onSuccess)
            .apply(this::addDisposable)
    }

    protected fun subscribeWithProgress(
        upstream: Completable,
        onComplete: () -> Unit = {},
        showProgress: (Disposable) -> Unit = { showProgress() },
        hideProgress: () -> Unit = this::hideProgress
    ) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .doOnSubscribe(showProgress)
            .doAfterTerminate(hideProgress)
            .subscribeBy(this::onError, onComplete)
            .apply(this::addDisposable)
    }

    protected fun <T : Any> subscribeWithProgress(
        upstream: Maybe<T>,
        onSuccess: (T) -> Unit,
        onComplete: () -> Unit,
        showProgress: (Disposable) -> Unit = { showProgress() },
        hideProgress: () -> Unit = this::hideProgress
    ) {
        upstream
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .doOnSubscribe(showProgress)
            .doAfterTerminate(hideProgress)
            .subscribeBy(this::onError, onSuccess = onSuccess, onComplete = onComplete)
            .apply(this::addDisposable)
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @CallSuper
    public override fun onCleared() {
        compositeDisposable.clear()
    }
}
