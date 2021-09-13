package com.senyk.volodymyr.schedulesapp.presentation.feature.schedules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import com.senyk.volodymyr.schedulesapp.domain.usecase.pairsmanagement.GetCurrentScheduleUseCase
import com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel.BaseRxViewModel
import javax.inject.Inject

class StudyWeekViewModel @Inject constructor(
    private val getCurrentScheduleUseCase: GetCurrentScheduleUseCase
) : BaseRxViewModel() {

    private val _currentSchedule = MutableLiveData<Schedule>()
    val currentSchedule: LiveData<Schedule>
        get() = _currentSchedule

    fun onStart() {
        subscribeWithProgress(
            upstream = getCurrentScheduleUseCase(),
            onSuccess = { _currentSchedule.value = it },
            onComplete = { _toastMessage.value = "Empty" /*    _dialogFragment.value =*/ }
        )
    }
}
