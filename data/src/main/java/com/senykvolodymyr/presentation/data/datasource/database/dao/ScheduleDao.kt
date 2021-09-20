package com.senykvolodymyr.presentation.data.datasource.database.dao

import androidx.room.*
import com.senykvolodymyr.presentation.data.datasource.database.entity.ScheduleDbo
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insert(newSchedule: ScheduleDbo): Single<Long>

    @Query("SELECT * FROM ${ScheduleDbo.TABLE_NAME}")
    internal abstract fun getAll(): Single<List<ScheduleDbo>>

    @Query("SELECT * FROM ${ScheduleDbo.TABLE_NAME} WHERE ${ScheduleDbo.ID} = :id")
    internal abstract fun getById(id: Long): Maybe<ScheduleDbo>

    @Query("SELECT * FROM ${ScheduleDbo.TABLE_NAME} WHERE ${ScheduleDbo.IS_CURRENT} = 1")
    internal abstract fun getCurrent(): Maybe<ScheduleDbo>

    @Update
    internal abstract fun update(schedule: ScheduleDbo): Completable

    @Query("DELETE FROM ${ScheduleDbo.TABLE_NAME} WHERE ${ScheduleDbo.ID} = :id")
    internal abstract fun deleteById(id: Long): Completable
}
