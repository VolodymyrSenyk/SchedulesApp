package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;

public class PairMapper extends BaseEntityDtoMapper<PairDataEntity, PairDto> {
    @Override
    public PairDto convertToDto(PairDataEntity entity) {
        PairType pairType;
        switch (entity.type) {
            case 1: pairType = PairType.LECTURE; break;
            case 2: pairType = PairType.PRACTICE; break;
            case 3: pairType = PairType.LABORATORY; break;
            case 4: pairType = PairType.SPORT; break;
            default: pairType = PairType.NOT_STATED;
        }
        return PairDto.getBuilder().setName(entity.name).setTime(entity.time).setTeacher(entity.teacher)
                .setType(pairType).setPlace(entity.place).setAdditionalInfo(entity.additionalInfo)
                .build();
    }

    @Override
    public PairDataEntity convertToEntity(PairDto dto) {
        PairDataEntity entity = new PairDataEntity();
        entity.name = dto.getName();
        entity.time = dto.getTime();
        entity.teacher = dto.getTeacher();
        switch (dto.getType()) {
            case LECTURE: entity.type = 1; break;
            case PRACTICE: entity.type = 2; break;
            case LABORATORY: entity.type = 3; break;
            case SPORT: entity.type = 4; break;
            default: entity.type = 0;
        }
        entity.place = dto.getPlace();
        entity.additionalInfo = dto.getAdditionalInfo();
        return entity;
    }
}
