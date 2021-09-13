package com.senyk.volodymyr.schedulesapp.domain.repository

import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleInfo
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue

interface ScheduleRepository {

    @CheckReturnValue
    fun createNewSchedule(schedule: ScheduleInfo): Single<String>

    @CheckReturnValue
    fun getSchedulesList(): Single<List<ScheduleInfo>>

    @CheckReturnValue
    fun getScheduleInfo(scheduleId: String): Single<ScheduleInfo>

    @CheckReturnValue
    fun getScheduleForOneDay(scheduleId: String, weekType: WeekType, dayType: DayType): Single<List<Pair>>

    @CheckReturnValue
    fun updateScheduleForOneDay(scheduleId: String, weekType: WeekType, dayType: DayType, pairs: List<Pair>): Single<List<Pair>>

    @CheckReturnValue
    fun deleteSchedule(scheduleId: String): Completable
}
