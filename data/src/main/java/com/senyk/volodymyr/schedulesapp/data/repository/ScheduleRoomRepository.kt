package com.senyk.volodymyr.schedulesapp.data.repository

import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.ScheduleDao
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toPair
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toPairDataEntity
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toScheduleDbo
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toScheduleInfo
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleInfo
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ScheduleRoomRepository @Inject constructor(
    private val scheduleDao: ScheduleDao,
) : ScheduleRepository {

    override fun createNewSchedule(schedule: ScheduleInfo): Single<String> =
        scheduleDao.insertSchedule(schedule.toScheduleDbo()).map { it.toString() }

    override fun getSchedulesList(): Single<List<ScheduleInfo>> =
        scheduleDao.getSchedules().map { list -> list.map { it.toScheduleInfo() } }

    override fun getScheduleInfo(scheduleId: String): Single<ScheduleInfo> =
        scheduleDao.getScheduleById(scheduleId.toLong()).map { it.toScheduleInfo() }

    override fun getScheduleForOneDay(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType
    ): Single<List<Pair>> = scheduleDao.getScheduleForOneDay(scheduleId, weekType, dayType)
        .map { list -> list.map { it.toPair() } }

    override fun updateScheduleForOneDay(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType,
        pairs: List<Pair>
    ): Single<List<Pair>> = scheduleDao.getScheduleForOneDay(scheduleId, weekType, dayType)
        .map { list -> list.map { it.id } }
        .toObservable()
        .flatMapIterable { it }
        .flatMapCompletable { scheduleDao.deletePairById(it) }
        .andThen {
            scheduleDao.insertPairs(
                pairs.map { it.toPairDataEntity(scheduleId.toLong(), weekType, dayType) }
            )
        }
        .andThen(scheduleDao.getScheduleForOneDay(scheduleId, weekType, dayType))
        .map { list -> list.map { it.toPair() } }

    override fun deleteSchedule(scheduleId: String): Completable =
        scheduleDao.deleteScheduleById(scheduleId.toLong())
}
