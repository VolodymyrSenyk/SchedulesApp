package com.senyk.volodymyr.schedulesapp.data.mapper.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.DayDto;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.DayDataEntity;

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
