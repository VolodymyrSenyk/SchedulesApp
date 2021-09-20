package com.senykvolodymyr.presentation.domain.usecase.pairsmanagement

import com.senykvolodymyr.presentation.domain.entity.Schedule
import com.senykvolodymyr.presentation.domain.repository.ScheduleRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetCurrentScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    operator fun invoke(): Maybe<Schedule> = scheduleRepository.getCurrentSchedule()
}
