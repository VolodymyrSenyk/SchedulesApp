package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;

public class ScheduleMapper extends BaseEntityDtoMapper<ScheduleDataEntity, ScheduleDto> {
    @Override
    public ScheduleDto convertToDto(ScheduleDataEntity entity) {
        return null;
    }

    @Override
    public ScheduleDataEntity convertToEntity(ScheduleDto dto) {
        return null;
    }
}
