package com.senykvolodymyr.presentation.feature.schedulesmanager

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senykvolodymyr.core.base.viewmodel.BaseRxViewModel
import com.senykvolodymyr.core.provider.ResourcesProvider
import com.senykvolodymyr.presentation.domain.entity.Schedule
import com.senykvolodymyr.presentation.domain.usecase.schedulesmanagement.ChangeCurrentScheduleUseCase
import com.senykvolodymyr.presentation.domain.usecase.schedulesmanagement.DeleteScheduleUseCase
import com.senykvolodymyr.presentation.domain.usecase.schedulesmanagement.GetAllSchedulesUseCase
import com.senykvolodymyr.presentation.domain.usecase.schedulesmanagement.SaveNewScheduleUseCase
import com.senykvolodymyr.presentation.feature.common.dialog.custom.CreateScheduleDialogFragment
import com.senykvolodymyr.presentation.feature.common.entity.ScheduleUi
import com.senykvolodymyr.presentation.feature.common.entity.toScheduleInfo
import com.senykvolodymyr.presentation.feature.common.entity.toScheduleUi
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
