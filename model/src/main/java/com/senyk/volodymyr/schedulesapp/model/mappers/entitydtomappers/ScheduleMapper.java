package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;

public class ScheduleMapper extends BaseEntityDtoMapper<ScheduleDataEntity, ScheduleDto> {
    @Override
    public ScheduleDto convertToDto(ScheduleDataEntity entity) {
        return new ScheduleDto(
                entity.name,
                entity.createdAt,
                entity.numberOfDays,
                entity.numberOfWeeks
        );
    }

    @Override
    public ScheduleDataEntity convertToEntity(ScheduleDto dto) {
        ScheduleDataEntity entity = new ScheduleDataEntity();
        entity.name = dto.getName();
        entity.numberOfDays = dto.getNumberOfDays();
        entity.numberOfWeeks = dto.getNumberOfWeeks();
        return entity;
    }
}
