package com.senyk.volodymyr.schedulesapp.domain.usecase.pairsmanagement

import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GetCurrentScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    operator fun invoke(): Maybe<Schedule> = scheduleRepository.getCurrentSchedule()
}
