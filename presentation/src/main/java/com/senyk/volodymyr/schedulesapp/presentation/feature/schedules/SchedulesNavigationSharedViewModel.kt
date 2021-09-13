package com.senyk.volodymyr.schedulesapp.presentation.feature.schedules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleInfo
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import com.senyk.volodymyr.schedulesapp.domain.repository.UserSettingsRepository
import com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel.BaseRxViewModel
import javax.inject.Inject

class SchedulesNavigationSharedViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val userSettingsRepository: UserSettingsRepository
) : BaseRxViewModel() {

    private val _currentSchedule = MutableLiveData<ScheduleInfo>()
    val currentSchedule: LiveData<ScheduleInfo>
        get() = _currentSchedule

    private val _currentWeekType = MutableLiveData<WeekType>()
    val currentWeekType: LiveData<WeekType>
        get() = _currentWeekType

    private val _currentDayType = MutableLiveData<DayType>()
    val currentDayType: LiveData<DayType>
        get() = _currentDayType

    fun onStart() {
        subscribeWithProgress(
            upstream = userSettingsRepository.getCurrentScheduleId(),
            onSuccess = { currentScheduleId ->
                if (currentScheduleId.isEmpty()) {
                //    _dialogFragment.value =
                } else {
                    loadScheduleInfo(currentScheduleId)
                }
            }
        )
    }

    fun onPause(currentWeekType: WeekType, currentDayType: DayType) {

    }

    private fun loadScheduleInfo(scheduleId: String) {
        subscribeWithProgress(
            upstream = scheduleRepository.getScheduleInfo(scheduleId),
            onSuccess = { schedule ->
                _currentSchedule.value = schedule
            }
        )
    }
}
