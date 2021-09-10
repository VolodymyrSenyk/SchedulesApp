package com.senyk.volodymyr.schedulesapp.data.mapper.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekDto;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.WeekDataEntity;

import java.util.Collections;

public class WeekEntityDtoMapper extends BaseEntityDtoMapper<WeekDataEntity, WeekDto> {
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
