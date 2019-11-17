package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.dto.WeekDto;

public class WeekMapper extends BaseEntityDtoMapper<WeekDataEntity, WeekDto> {
    @Override
    public WeekDto convertToDto(WeekDataEntity entity) {
        return null;
    }

    @Override
    public WeekDataEntity convertToEntity(WeekDto dto) {
        return null;
    }
}
