package com.senyk.volodymyr.schedulesapp.data.mapper.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.ScheduleDataEntity;

public class ScheduleEntityDtoMapper extends BaseEntityDtoMapper<ScheduleDataEntity, Schedule> {
    @Override
    public Schedule convertToDto(ScheduleDataEntity entity) {
        return new Schedule(
                entity.name,
                entity.createdAt,
                entity.numberOfDays,
                entity.numberOfWeeks
        );
    }

    @Override
    public ScheduleDataEntity convertToEntity(Schedule dto) {
        ScheduleDataEntity entity = new ScheduleDataEntity();
        entity.name = dto.getName();
        entity.numberOfDays = dto.getNumberOfDays();
        entity.numberOfWeeks = dto.getNumberOfWeeks();
        return entity;
    }
}
