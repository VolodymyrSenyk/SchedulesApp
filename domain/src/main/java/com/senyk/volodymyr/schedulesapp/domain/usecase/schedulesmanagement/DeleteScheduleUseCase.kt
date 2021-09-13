package com.senyk.volodymyr.schedulesapp.domain.usecase.schedulesmanagement

import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import io.reactivex.Single
import javax.inject.Inject

class DeleteScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    operator fun invoke(schedule: Schedule): Single<List<Schedule>> =
        scheduleRepository.deleteScheduleById(schedule.id)
            .andThen(scheduleRepository.getAllSchedules())
}
