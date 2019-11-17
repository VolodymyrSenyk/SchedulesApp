package com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "schedules")
public class ScheduleDataEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "schedule_id")
    public long id;

    @NonNull
    @ColumnInfo(name = "schedule_name")
    public String name;

    @NonNull
    @ColumnInfo(name = "schedule_createdAt")
    public long createdAt = Calendar.getInstance().getTimeInMillis();

    @NonNull
    @ColumnInfo(name = "schedule_numberOfDays")
    public int numberOfDays;

    @NonNull
    @ColumnInfo(name = "schedule_numberOfWeeks")
    public int numberOfWeeks;
}
