package com.senykvolodymyr.presentation.domain.usecase.pairsmanagement

import com.senykvolodymyr.presentation.domain.entity.DayType
import com.senykvolodymyr.presentation.domain.repository.ScheduleRepository
import io.reactivex.Maybe
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

class GetCurrentDayTypeUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    operator fun invoke(): Maybe<DayType> = scheduleRepository.getCurrentSchedule()
        .map { schedule ->
            when (LocalDate.now().dayOfWeek) {
                DayOfWeek.MONDAY -> DayType.MONDAY
                DayOfWeek.TUESDAY -> DayType.TUESDAY
                DayOfWeek.WEDNESDAY -> DayType.WEDNESDAY
                DayOfWeek.THURSDAY -> DayType.THURSDAY
                DayOfWeek.FRIDAY -> DayType.FRIDAY
                DayOfWeek.SATURDAY -> if (schedule.isSaturdayWorkingDay) DayType.SATURDAY else DayType.MONDAY
                DayOfWeek.SUNDAY -> DayType.MONDAY
                null -> DayType.MONDAY
            }
        }
}
