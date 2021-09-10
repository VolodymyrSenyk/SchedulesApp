package com.senyk.volodymyr.schedulesapp.data.datasource.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.senyk.volodymyr.schedulesapp.data.exception.SqlQueryExecutionException;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.DayEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.ScheduleEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.PairDataEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class PairsManagementDao {
    @Transaction
    @Query("SELECT * FROM schedules WHERE schedules.schedule_name = :scheduleName")
    public abstract Single<ScheduleEntity> getScheduleByName(String scheduleName);

    @Transaction
    @Query("SELECT days.day_id, days.day_ordinalNumber, days.week_id FROM days " +
            "INNER JOIN weeks ON days.week_id = weeks.week_id " +
            "INNER JOIN schedules ON weeks.schedule_id = schedules.schedule_id " +
            "WHERE schedules.schedule_name = :scheduleName " +
            "AND weeks.week_ordinalNumber = :weekOrdinal " +
            "AND days.day_ordinalNumber = :dayOrdinal")
    public abstract Single<DayEntity> getSchedule(String scheduleName, int weekOrdinal, int dayOrdinal);

    @Transaction
    public Completable updateSchedule(String scheduleName, int weekOrdinal, int dayOrdinal, DayEntity newSchedule) {
        long scheduleId = getScheduleIdByName(scheduleName);
        long weekId = getWeekIdByOrdinalNumber(scheduleId, weekOrdinal);
        long dayId = getDayIdByOrdinalNumber(weekId, dayOrdinal);
        List<Long> newPairsIds = new ArrayList<>();
        if (delete(dayId) > 1)
            return Completable.error(new SqlQueryExecutionException("Schedule for more than one day deleted"));
        newSchedule.day.weekId = weekId;
        long newDayId = insert(newSchedule.day);
        for (PairDataEntity pair : newSchedule.pairs) {
            pair.dayId = newDayId;
            newPairsIds.add(insert(pair));
        }
        if (!newSchedule.pairs.isEmpty() && newPairsIds.isEmpty())
            return Completable.error(new SqlQueryExecutionException("No new pairs created"));
        return Completable.complete();
    }

    @Query("SELECT schedules.schedule_id FROM schedules WHERE schedules.schedule_name = :scheduleName")
    abstract long getScheduleIdByName(String scheduleName);

    @Query("SELECT weeks.week_id FROM weeks WHERE weeks.schedule_id = :scheduleId AND weeks.week_ordinalNumber = :weekOrdinal")
    abstract long getWeekIdByOrdinalNumber(long scheduleId, int weekOrdinal);

    @Query("SELECT days.day_id FROM days WHERE days.week_id = :weekId AND days.day_ordinalNumber = :dayOrdinal")
    abstract long getDayIdByOrdinalNumber(long weekId, int dayOrdinal);

    @Query("DELETE FROM days WHERE days.day_id = :dayId")
    abstract int delete(long dayId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long insert(DayDataEntity newDay);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long insert(PairDataEntity pair);
}
