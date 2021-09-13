package com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel

import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.senyk.volodymyr.schedulesapp.presentation.core.entity.MessageWithAction
import com.senyk.volodymyr.schedulesapp.presentation.core.livedata.NavigationEventLiveData
import com.senyk.volodymyr.schedulesapp.presentation.core.livedata.SingleEventLiveData
import com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.HandledEvent
import com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.navigation.NavigationEvent

abstract class BaseViewModel : ViewModel() {

    protected val tag: String = this.javaClass.simpleName

    protected var _toastMessage = SingleEventLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    protected var _snackbarMessage = SingleEventLiveData<String>()
    val snackbarMessage: LiveData<String>
        get() = _snackbarMessage

    protected var _snackbarMessageWithAction = SingleEventLiveData<MessageWithAction>()
    val snackbarMessageWithAction: LiveData<MessageWithAction>
        get() = _snackbarMessageWithAction

    protected var _dialogFragment = SingleEventLiveData<DialogFragment>()
    val dialogFragment: LiveData<DialogFragment>
        get() = _dialogFragment

    protected var _showProgress = SingleEventLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    protected var _navigationEvent = NavigationEventLiveData()
    val navigationEvent: LiveData<HandledEvent<NavigationEvent>>
        get() = _navigationEvent

    protected open fun onError(throwable: Throwable) {
        hideProgress()
        Log.e(tag, "An error occurred: ${throwable.message}", throwable)
    }

    protected open fun showProgress() {
        _showProgress.value = true
    }

    protected open fun hideProgress() {
        _showProgress.value = false
    }
}
