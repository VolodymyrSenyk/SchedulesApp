package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;

public class PairMapper extends BaseEntityDtoMapper<PairDataEntity, PairDto> {
    @Override
    public PairDto convertToDto(PairDataEntity entity) {
        return null;
    }

    @Override
    public PairDataEntity convertToEntity(PairDto dto) {
        return null;
    }
}
