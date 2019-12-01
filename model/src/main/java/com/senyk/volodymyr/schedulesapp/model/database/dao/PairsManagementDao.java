package com.senyk.volodymyr.schedulesapp.model.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.senyk.volodymyr.schedulesapp.model.exceptions.SQLiteQueryExecutingException;
import com.senyk.volodymyr.schedulesapp.model.models.entities.DayEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.ScheduleEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class PairsManagementDao {
    @Transaction
    @Query("SELECT * FROM schedules WHERE schedules.schedule_name = :scheduleName")
    public abstract Single<ScheduleEntity> getScheduleByName(String scheduleName);

    @Transaction
    @Query("SELECT days.day_id, days.day_ordinal, days.week_id FROM days " +
            "INNER JOIN weeks ON days.week_id = weeks.week_id " +
            "INNER JOIN schedules ON weeks.schedule_id = schedules.schedule_id " +
            "WHERE schedules.schedule_name = :scheduleName " +
            "AND weeks.week_ordinal = :weekOrdinal " +
            "AND days.day_ordinal = :dayOrdinal")
    public abstract Single<DayEntity> getSchedule(String scheduleName, int weekOrdinal, int dayOrdinal);

    @Transaction
    public Completable updateSchedule(String scheduleName, int weekOrdinal, int dayOrdinal, DayEntity newSchedule) {
        long scheduleId = getScheduleIdByName(scheduleName);
        long weekId = getWeekIdByOrdinalNumber(scheduleId, weekOrdinal);
        long dayId = getDayIdByOrdinalNumber(weekId, dayOrdinal);
        if (delete(dayId) > 1) {
            return Completable.error(new SQLiteQueryExecutingException("More than one schedule deleted"));
        }
        newSchedule.day.weekId = weekId;
        long newDayId = insert(newSchedule.day);
        for (PairDataEntity pair : newSchedule.pairs) {
            pair.dayId = newDayId;
            insert(pair);
        }
        return Completable.complete();
    }

    @Query("SELECT schedules.schedule_id FROM schedules WHERE schedules.schedule_name = :scheduleName")
    public abstract long getScheduleIdByName(String scheduleName);

    @Query("SELECT weeks.week_id FROM weeks WHERE weeks.schedule_id = :scheduleId AND weeks.week_ordinal = :weekOrdinal")
    public abstract long getWeekIdByOrdinalNumber(long scheduleId, int weekOrdinal);

    @Query("SELECT days.day_id FROM days WHERE days.week_id = :weekId AND days.day_ordinal = :dayOrdinal")
    public abstract long getDayIdByOrdinalNumber(long weekId, int dayOrdinal);

    @Query("DELETE FROM days WHERE days.day_id = :dayId")
    public abstract int delete(long dayId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(DayDataEntity newDay);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(PairDataEntity pair);
}
