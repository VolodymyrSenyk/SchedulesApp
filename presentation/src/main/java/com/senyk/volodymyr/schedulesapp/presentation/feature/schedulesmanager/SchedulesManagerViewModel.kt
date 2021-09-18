package com.senyk.volodymyr.schedulesapp.presentation.feature.schedulesmanager

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import com.senyk.volodymyr.schedulesapp.domain.usecase.schedulesmanagement.ChangeCurrentScheduleUseCase
import com.senyk.volodymyr.schedulesapp.domain.usecase.schedulesmanagement.DeleteScheduleUseCase
import com.senyk.volodymyr.schedulesapp.domain.usecase.schedulesmanagement.GetAllSchedulesUseCase
import com.senyk.volodymyr.schedulesapp.domain.usecase.schedulesmanagement.SaveNewScheduleUseCase
import com.senyk.volodymyr.schedulesapp.presentation.core.base.viewmodel.BaseRxViewModel
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.ResourcesProvider
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.dialog.CreateScheduleDialogFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.ScheduleUi
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.toScheduleInfo
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.toScheduleUi
import javax.inject.Inject

class SchedulesManagerViewModel @Inject constructor(
    private val saveNewScheduleUseCase: SaveNewScheduleUseCase,
    private val getAllSchedulesUseCase: GetAllSchedulesUseCase,
    private val changeCurrentScheduleUseCase: ChangeCurrentScheduleUseCase,
    private val deleteScheduleUseCase: DeleteScheduleUseCase,
    private val resourcesProvider: ResourcesProvider
) : BaseRxViewModel() {

    private val _schedules = MutableLiveData<List<ScheduleUi>>()
    val schedules: LiveData<List<ScheduleUi>>
        get() = _schedules

    fun onStart() {
        subscribe(
            upstream = getAllSchedulesUseCase(),
            onSuccess = { setSchedules(it) }
        )
    }

    fun onDelete(schedule: ScheduleUi) {
        // can not delete current schedule
        subscribe(
            upstream = deleteScheduleUseCase(schedule.toScheduleInfo()),
            onSuccess = { setSchedules(it) }
        )
    }

    fun onSetNewCurrentSchedule(schedule: ScheduleUi) {
        subscribe(
            upstream = changeCurrentScheduleUseCase(schedule.toScheduleInfo()),
            onSuccess = { setSchedules(it) }
        )
    }

    override fun onDialogResultReceived(result: Parcelable) {
        if (result is CreateScheduleDialogFragment.Create) {
            val newSchedule = Schedule(
                name = result.scheduleName,
                isSaturdayWorkingDay = result.isSaturdayWorkingDay,
                isNumeratorDenominatorSystem = result.isNumeratorDenominatorSystem,
                isCurrent = true
            )
            subscribe(
                upstream = saveNewScheduleUseCase(newSchedule),
                onSuccess = { setSchedules(it) }
            )
        }
    }

    private fun setSchedules(schedules: List<Schedule>) {
        if (schedules.isEmpty()) {
            //     isNoSchedules.setValue(true)
        } else {
            _schedules.value = schedules.map { it.toScheduleUi(resourcesProvider) }
        }
    }
}
