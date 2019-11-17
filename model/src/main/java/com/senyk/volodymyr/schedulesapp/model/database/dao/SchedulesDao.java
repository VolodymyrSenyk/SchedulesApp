package com.senyk.volodymyr.schedulesapp.model.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.senyk.volodymyr.schedulesapp.model.models.entities.ScheduleEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class SchedulesDao {
    @Query("SELECT * FROM schedules WHERE schedules.schedule_name = :scheduleName")
    public abstract Single<ScheduleDataEntity> getScheduleDataByName(String scheduleName);

    @Transaction
    @Query("SELECT * FROM schedules WHERE schedules.schedule_name = :scheduleName")
    public abstract Single<ScheduleEntity> getScheduleByName(String scheduleName);

    @Transaction
    public Completable createNewSchedule(ScheduleDataEntity newSchedule) {
        long newScheduleId = insert(newSchedule);
        for (int i = 1; i <= newSchedule.numberOfWeeks; i++) {
            WeekDataEntity newWeek = new WeekDataEntity();
            newWeek.scheduleId = newScheduleId;
            newWeek.weekOrdinal = i;
            long newWeekId = insert(newWeek);
            for (int j = 1; j <= newSchedule.numberOfDays; j++) {
                DayDataEntity newDay = new DayDataEntity();
                newDay.weekId = newWeekId;
                newDay.dayOrdinal = j;
                insert(newDay);
            }
        }
        return Completable.complete();
    }

    @Query("DELETE FROM schedules WHERE schedules.schedule_name = :scheduleName")
    public abstract int deleteSchedule(String scheduleName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(ScheduleDataEntity newSchedule);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(WeekDataEntity newWeek);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(DayDataEntity newDay);
}
