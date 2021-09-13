package com.senyk.volodymyr.schedulesapp.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.PairDbo
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.ScheduleDbo
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class ScheduleDao {

    @Query("SELECT * FROM ${ScheduleDbo.TABLE_NAME}")
    internal abstract fun getSchedules(): Single<List<ScheduleDbo>>

    @Query("SELECT * FROM ${ScheduleDbo.TABLE_NAME} WHERE ${ScheduleDbo.ID} = :scheduleId")
    internal abstract fun getScheduleById(scheduleId: Long): Single<ScheduleDbo>

    @Query("SELECT * FROM ${PairDbo.TABLE_NAME} WHERE ${PairDbo.SCHEDULE_ID} = :scheduleId AND ${PairDbo.WEEK_TYPE} = :weekType AND ${PairDbo.DAY_TYPE} = :dayType")
    internal abstract fun getScheduleForOneDay(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType
    ): Single<List<PairDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertSchedule(newSchedule: ScheduleDbo): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertPair(newPair: PairDbo): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertPairs(newPairs: List<PairDbo>): Completable

    @Query("DELETE FROM ${ScheduleDbo.TABLE_NAME} WHERE ${ScheduleDbo.ID} = :scheduleId")
    internal abstract fun deleteScheduleById(scheduleId: Long): Completable

    @Query("DELETE FROM ${PairDbo.TABLE_NAME} WHERE ${PairDbo.ID} = :pairId")
    internal abstract fun deletePairById(pairId: Long): Completable
}
