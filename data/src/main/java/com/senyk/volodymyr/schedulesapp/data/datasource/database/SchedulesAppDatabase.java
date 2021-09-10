package com.senyk.volodymyr.schedulesapp.data.datasource.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.PairsManagementDao;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.SchedulesManagementDao;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.WeekDataEntity;

@Database(entities = {ScheduleDataEntity.class, WeekDataEntity.class, DayDataEntity.class, PairDataEntity.class}, version = 1)
public abstract class SchedulesAppDatabase extends RoomDatabase {
    public static final String SCHEDULES_APP_DATABASE_NAME = "schedulesApp";

    public abstract SchedulesManagementDao getSchedulesManagementDao();

    public abstract PairsManagementDao getPairsManagementDao();
}
