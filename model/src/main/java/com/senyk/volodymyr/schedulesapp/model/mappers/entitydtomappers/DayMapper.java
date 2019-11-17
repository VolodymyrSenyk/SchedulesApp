package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.dto.DayDto;

public class DayMapper extends BaseEntityDtoMapper<DayDataEntity, DayDto> {
    @Override
    public DayDto convertToDto(DayDataEntity entity) {
        return null;
    }

    @Override
    public DayDataEntity convertToEntity(DayDto dto) {
        return null;
    }
}
