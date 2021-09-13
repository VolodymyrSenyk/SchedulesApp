package com.senyk.volodymyr.schedulesapp.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.PairDbo
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class PairDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insert(newPair: PairDbo): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertAll(newPairs: List<PairDbo>): Single<List<Long>>

    @Query("SELECT * FROM ${PairDbo.TABLE_NAME} WHERE ${PairDbo.SCHEDULE_ID} = :scheduleId AND ${PairDbo.WEEK_TYPE} = :weekType AND ${PairDbo.DAY_TYPE} = :dayType")
    internal abstract fun getByFilters(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType
    ): Single<List<PairDbo>>

    @Query("DELETE FROM ${PairDbo.TABLE_NAME} WHERE ${PairDbo.ID} = :id")
    internal abstract fun deleteById(id: Long): Completable

    @Query("DELETE FROM ${PairDbo.TABLE_NAME} WHERE ${PairDbo.SCHEDULE_ID} = :scheduleId AND ${PairDbo.WEEK_TYPE} = :weekType AND ${PairDbo.DAY_TYPE} = :dayType")
    internal abstract fun deleteByFilters(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType
    ): Completable
}
