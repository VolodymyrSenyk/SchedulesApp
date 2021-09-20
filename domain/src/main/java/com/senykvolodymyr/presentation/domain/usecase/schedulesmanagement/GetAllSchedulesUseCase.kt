package com.senykvolodymyr.presentation.domain.usecase.schedulesmanagement

import com.senykvolodymyr.presentation.domain.entity.Schedule
import com.senykvolodymyr.presentation.domain.repository.ScheduleRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllSchedulesUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    operator fun invoke(): Single<List<Schedule>> = scheduleRepository.getAllSchedules()
}
