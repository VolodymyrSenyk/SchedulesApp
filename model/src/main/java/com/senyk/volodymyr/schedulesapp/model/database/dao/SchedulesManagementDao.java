package com.senyk.volodymyr.schedulesapp.model.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.senyk.volodymyr.schedulesapp.model.exceptions.SqlQueryExecutionException;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class SchedulesManagementDao {
    @Query("SELECT * FROM schedules")
    public abstract Single<List<ScheduleDataEntity>> getSchedulesList();

    @Transaction
    public Completable createNewSchedule(ScheduleDataEntity newSchedule) {
        List<Long> newDaysIds = new ArrayList<>();
        long newScheduleId = insert(newSchedule);
        for (int weekOrdinal = 1; weekOrdinal <= newSchedule.numberOfWeeks; weekOrdinal++) {
            WeekDataEntity newWeek = new WeekDataEntity();
            newWeek.scheduleId = newScheduleId;
            newWeek.weekOrdinal = weekOrdinal;
            long newWeekId = insert(newWeek);
            for (int dayOrdinal = 1; dayOrdinal <= newSchedule.numberOfDays; dayOrdinal++) {
                DayDataEntity newDay = new DayDataEntity();
                newDay.weekId = newWeekId;
                newDay.dayOrdinal = dayOrdinal;
                newDaysIds.add(insert(newDay));
            }
        }
        if (newDaysIds.isEmpty())
            return Completable.error(new SqlQueryExecutionException("No new days created"));
        return Completable.complete();
    }

    public Completable deleteSchedule(String scheduleName) {
        return delete(scheduleName) != 1 ?
                Completable.error(new SqlQueryExecutionException("More than one schedule deleted")) :
                Completable.complete();
    }

    @Query("DELETE FROM schedules WHERE schedules.schedule_name = :scheduleName")
    abstract int delete(String scheduleName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long insert(ScheduleDataEntity newSchedule);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long insert(WeekDataEntity newWeek);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long insert(DayDataEntity newDay);
}
