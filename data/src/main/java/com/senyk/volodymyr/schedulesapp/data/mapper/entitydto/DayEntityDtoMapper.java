package com.senyk.volodymyr.schedulesapp.data.mapper.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.Day;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.DayDataEntity;

import java.util.Collections;

public class DayEntityDtoMapper extends BaseEntityDtoMapper<DayDataEntity, Day> {
    @Override
    public Day convertToDto(DayDataEntity entity) {
        return new Day(
                entity.dayOrdinal,
                Collections.emptyList()
        );
    }

    @Override
    public DayDataEntity convertToEntity(Day dto) {
        DayDataEntity entity = new DayDataEntity();
        entity.dayOrdinal = dto.getOrdinalNumber();
        return entity;
    }
}
