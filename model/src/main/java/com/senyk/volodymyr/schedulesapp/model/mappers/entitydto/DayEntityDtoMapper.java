package com.senyk.volodymyr.schedulesapp.model.mappers.entitydto;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.dto.DayDto;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.DayDataEntity;

import java.util.Collections;

public class DayEntityDtoMapper extends BaseEntityDtoMapper<DayDataEntity, DayDto> {
    @Override
    public DayDto convertToDto(DayDataEntity entity) {
        return new DayDto(
                entity.dayOrdinal,
                Collections.emptyList()
        );
    }

    @Override
    public DayDataEntity convertToEntity(DayDto dto) {
        DayDataEntity entity = new DayDataEntity();
        entity.dayOrdinal = dto.getOrdinalNumber();
        return entity;
    }
}
