package com.senyk.volodymyr.schedulesapp.data.datasource.database.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.WeekDataEntity;

import java.util.List;

public class ScheduleEntity {
    @Embedded
    public ScheduleDataEntity schedule;

    @Relation(parentColumn = "schedule_id", entity = WeekDataEntity.class, entityColumn = "schedule_id")
    public List<WeekDataEntity> weeks;
}
