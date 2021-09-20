package com.senykvolodymyr.presentation.feature.schedules

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senykvolodymyr.core.base.viewmodel.BaseRxViewModel
import com.senykvolodymyr.presentation.domain.entity.Schedule
import com.senykvolodymyr.presentation.domain.usecase.pairsmanagement.GetCurrentScheduleUseCase
import com.senykvolodymyr.presentation.domain.usecase.schedulesmanagement.SaveNewScheduleUseCase
import com.senykvolodymyr.presentation.feature.common.dialog.custom.CreateScheduleDialogFragment
import javax.inject.Inject

class StudyWeekViewModel @Inject constructor(
    private val getCurrentScheduleUseCase: GetCurrentScheduleUseCase,
    private val saveNewScheduleUseCase: SaveNewScheduleUseCase
) : BaseRxViewModel() {

    private val _currentSchedule = MutableLiveData<Schedule>()
    val currentSchedule: LiveData<Schedule>
        get() = _currentSchedule

    fun onStart() {
        subscribeWithProgress(
            upstream = getCurrentScheduleUseCase(),
            onSuccess = { _currentSchedule.value = it },
            onComplete = {
                _dialogFragment.value = CreateScheduleDialogFragment.newInstance(
                    isFirstSchedule = true
                )
            }
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
                onSuccess = { _currentSchedule.value = it.find { schedule -> schedule.isCurrent } }
            )
        }
    }
}
