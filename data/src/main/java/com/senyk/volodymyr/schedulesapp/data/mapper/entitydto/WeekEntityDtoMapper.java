package com.senyk.volodymyr.schedulesapp.data.mapper.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.Week;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.WeekDataEntity;

import java.util.Collections;

public class WeekEntityDtoMapper extends BaseEntityDtoMapper<WeekDataEntity, Week> {
    @Override
    public Week convertToDto(WeekDataEntity entity) {
        return new Week(
                entity.weekOrdinal,
                Collections.emptyList()
        );
    }

    @Override
    public WeekDataEntity convertToEntity(Week dto) {
        WeekDataEntity entity = new WeekDataEntity();
        entity.weekOrdinal = dto.getOrdinalNumber();
        return entity;
    }
}
