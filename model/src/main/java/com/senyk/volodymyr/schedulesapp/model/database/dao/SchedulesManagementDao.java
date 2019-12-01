package com.senyk.volodymyr.schedulesapp.model.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.senyk.volodymyr.schedulesapp.model.exceptions.SQLiteQueryExecutingException;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class SchedulesManagementDao {
    @Query("SELECT * FROM schedules")
    public abstract Single<List<ScheduleDataEntity>> getSchedulesList();

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

    public Completable deleteSchedule(String scheduleName) {
        return delete(scheduleName) != 1 ?
                Completable.error(new SQLiteQueryExecutingException("More than one schedule deleted")) :
                Completable.complete();
    }

    @Query("DELETE FROM schedules WHERE schedules.schedule_name = :scheduleName")
    public abstract int delete(String scheduleName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(ScheduleDataEntity newSchedule);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(WeekDataEntity newWeek);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(DayDataEntity newDay);
}
