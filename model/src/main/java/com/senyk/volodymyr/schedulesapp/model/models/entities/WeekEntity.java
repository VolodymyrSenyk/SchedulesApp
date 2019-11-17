package com.senyk.volodymyr.schedulesapp.model.models.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;

import java.util.List;

public class WeekEntity {
    @Embedded
    public WeekDataEntity week;

    @Relation(parentColumn = "week_id", entity = DayDataEntity.class, entityColumn = "week_id")
    public List<DayDataEntity> days;
}
