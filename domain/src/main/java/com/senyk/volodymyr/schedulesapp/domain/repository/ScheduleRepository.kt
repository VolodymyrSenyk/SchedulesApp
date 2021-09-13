package com.senyk.volodymyr.schedulesapp.domain.repository

import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue

interface ScheduleRepository {

    @CheckReturnValue
    fun saveNewSchedule(schedule: Schedule): Single<String>

    @CheckReturnValue
    fun getAllSchedules(): Single<List<Schedule>>

    @CheckReturnValue
    fun getScheduleById(scheduleId: String): Maybe<Schedule>

    @CheckReturnValue
    fun getCurrentSchedule(): Maybe<Schedule>

    @CheckReturnValue
    fun changeCurrentSchedule(newCurrentScheduleId: String): Completable

    @CheckReturnValue
    fun deleteScheduleById(scheduleId: String): Completable
}
