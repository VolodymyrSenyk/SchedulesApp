package com.senyk.volodymyr.schedulesapp.data.repository

import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.ScheduleDao
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toSchedule
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toScheduleDbo
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class ScheduleRoomRepository @Inject constructor(
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {

    override fun saveNewSchedule(schedule: Schedule): Single<String> =
        scheduleDao.insert(schedule.toScheduleDbo().copy(id = 0)).map { it.toString() }

    override fun getAllSchedules(): Single<List<Schedule>> =
        scheduleDao.getAll().map { schedules -> schedules.map { it.toSchedule() } }

    override fun getScheduleById(scheduleId: String): Maybe<Schedule> =
        scheduleDao.getById(scheduleId.toLong()).map { it.toSchedule() }

    override fun getCurrentSchedule(): Maybe<Schedule> =
        scheduleDao.getCurrent().map { it.toSchedule() }

    override fun changeCurrentSchedule(newCurrentScheduleId: String): Completable =
        scheduleDao.getCurrent()
            .flatMapCompletable { scheduleDao.update(it.copy(isCurrent = false)) }
            .andThen(scheduleDao.getById(newCurrentScheduleId.toLong()))
            .flatMapCompletable { scheduleDao.update(it.copy(isCurrent = true)) }

    override fun deleteScheduleById(scheduleId: String): Completable =
        scheduleDao.deleteById(scheduleId.toLong())
}
