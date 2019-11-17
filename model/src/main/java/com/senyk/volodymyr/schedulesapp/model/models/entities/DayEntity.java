package com.senyk.volodymyr.schedulesapp.model.models.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;

import java.util.List;

public class DayEntity {
    @Embedded
    public DayDataEntity day;

    @Relation(parentColumn = "day_id", entity = PairDataEntity.class, entityColumn = "day_id")
    public List<PairDataEntity> pairs;
}
