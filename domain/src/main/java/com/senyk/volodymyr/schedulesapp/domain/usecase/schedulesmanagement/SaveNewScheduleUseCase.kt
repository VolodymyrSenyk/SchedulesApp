package com.senyk.volodymyr.schedulesapp.domain.usecase.schedulesmanagement

import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import io.reactivex.Single
import javax.inject.Inject

class SaveNewScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    operator fun invoke(schedule: Schedule): Single<List<Schedule>> =
        scheduleRepository.saveNewSchedule(schedule.copy(id = "-1"))
            .flatMapCompletable { scheduleRepository.changeCurrentSchedule(it) }
            .andThen(scheduleRepository.getAllSchedules())
}
