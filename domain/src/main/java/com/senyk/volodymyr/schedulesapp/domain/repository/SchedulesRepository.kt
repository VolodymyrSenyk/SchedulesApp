package com.senyk.volodymyr.schedulesapp.domain.repository

import com.senyk.volodymyr.schedulesapp.domain.entity.Day
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import com.senyk.volodymyr.schedulesapp.domain.entity.Week
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue

interface SchedulesRepository {

    @CheckReturnValue
    fun createNewSchedule(schedule: Schedule): Completable

    @CheckReturnValue
    fun getSchedulesList(): Single<List<Schedule>>

    @CheckReturnValue
    fun deleteSchedule(scheduleName: String): Completable

    @CheckReturnValue
    fun updateSchedule(scheduleName: String, weekNumber: Int, day: Day): Completable

    @CheckReturnValue
    fun getScheduleForOneDay(scheduleName: String, weekNumber: Int, dayNumber: Int): Single<Day>

    @CheckReturnValue
    fun getFullSchedule(scheduleName: String): Single<List<Week>>
}
