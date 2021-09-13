package com.senyk.volodymyr.schedulesapp.presentation.feature.schedulesmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import com.senyk.volodymyr.schedulesapp.domain.repository.UserSettingsRepository
import com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel.BaseRxViewModel
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.ResourcesProvider
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.ScheduleUi
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.toScheduleUi
import javax.inject.Inject

class SchedulesManagerViewModel @Inject constructor(
    private val schedulesRepository: ScheduleRepository,
    private val userSettingsRepository: UserSettingsRepository,
    private val resourcesProvider: ResourcesProvider
) : BaseRxViewModel() {

    private val _schedules = MutableLiveData<List<ScheduleUi>>()
    val schedules: LiveData<List<ScheduleUi>>
        get() = _schedules

    fun onStart() {
        loadSchedules()
    }

    fun onDelete(schedule: ScheduleUi) {
        // can not delete current schedule
        subscribe(
            upstream = schedulesRepository.deleteSchedule(schedule.id),
            onComplete = {
                loadSchedules()
            }
        )
    }

    fun onSetNewCurrentSchedule(newCurrentScheduleId: String) {
        subscribe(
            upstream = userSettingsRepository.setCurrentScheduleId(newCurrentScheduleId),
            onComplete = {
                loadSchedules()
            }
        )
    }

    private fun loadSchedules() {
        subscribe(
            upstream = schedulesRepository.getSchedulesList(),
            onSuccess = { schedules ->
                if (schedules.isEmpty()) {
                    //     isNoSchedules.setValue(true)
                } else {
                    _schedules.value = schedules.map { it.toScheduleUi(resourcesProvider) }
                }
            }
        )
    }
}
