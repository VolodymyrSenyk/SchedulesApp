package com.senyk.volodymyr.schedulesapp.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.senyk.volodymyr.schedulesapp.model.database.dao.PairsDao;
import com.senyk.volodymyr.schedulesapp.model.database.dao.SchedulesDao;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;

@Database(entities = {ScheduleDataEntity.class, WeekDataEntity.class, DayDataEntity.class, PairDataEntity.class}, version = 1)
public abstract class SchedulesAppDatabase extends RoomDatabase {
    public static final String SCHEDULES_APP_DATABASE_NAME = "schedulesApp";

    public abstract SchedulesDao getSchedulesDao();

    public abstract PairsDao getPairsDao();
}
