package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.dto.WeekDto;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;

import java.util.Collections;

public class WeekMapper extends BaseEntityDtoMapper<WeekDataEntity, WeekDto> {
    @Override
    public WeekDto convertToDto(WeekDataEntity entity) {
        return new WeekDto(
                entity.weekOrdinal,
                Collections.emptyList()
        );
    }

    @Override
    public WeekDataEntity convertToEntity(WeekDto dto) {
        WeekDataEntity entity = new WeekDataEntity();
        entity.weekOrdinal = dto.getOrdinalNumber();
        return entity;
    }
}
