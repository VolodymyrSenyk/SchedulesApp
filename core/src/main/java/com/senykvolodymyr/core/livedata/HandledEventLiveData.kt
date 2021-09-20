package com.senykvolodymyr.core.livedata

import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.senykvolodymyr.core.livedata.event.HandledEvent

open class HandledEventLiveData<T> : MutableLiveData<HandledEvent<T>>() {

    @MainThread
    fun setHandledValue(value: T) {
        super.setValue(HandledEvent(value))
    }

    @AnyThread
    fun postHandledValue(value: T) {
        super.postValue(HandledEvent(value))
    }
}
