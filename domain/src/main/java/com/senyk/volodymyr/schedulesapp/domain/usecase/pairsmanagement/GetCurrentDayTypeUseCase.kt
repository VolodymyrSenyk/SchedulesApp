package com.senyk.volodymyr.schedulesapp.domain.usecase.pairsmanagement

import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import io.reactivex.Maybe
import io.reactivex.Single
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
