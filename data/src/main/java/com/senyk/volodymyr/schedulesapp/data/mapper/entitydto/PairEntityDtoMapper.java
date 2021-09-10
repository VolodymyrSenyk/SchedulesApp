package com.senyk.volodymyr.schedulesapp.data.mapper.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.PairDto;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.domain.entity.PairType;
import com.senyk.volodymyr.schedulesapp.domain.entity.PairTypeEnumContract;

public class PairEntityDtoMapper extends BaseEntityDtoMapper<PairDataEntity, PairDto> {
    @Override
    public PairDto convertToDto(PairDataEntity entity) {
        PairType pairType;
        switch (entity.type) {
            case PairTypeEnumContract.LECTURE_TYPE_INDEX:
                pairType = PairType.LECTURE;
                break;
            case PairTypeEnumContract.PRACTICE_TYPE_INDEX:
                pairType = PairType.PRACTICE;
                break;
            case PairTypeEnumContract.LABORATORY_TYPE_INDEX:
                pairType = PairType.LABORATORY;
                break;
            case PairTypeEnumContract.SPORT_TYPE_INDEX:
                pairType = PairType.SPORT;
                break;
            default:
                pairType = PairType.NOT_STATED;
        }
        return new PairDto(entity.time, entity.name, entity.teacher, pairType, entity.place, entity.additionalInfo);
    }

    @Override
    public PairDataEntity convertToEntity(PairDto dto) {
        PairDataEntity entity = new PairDataEntity();
        entity.name = dto.getName();
        entity.time = dto.getTime();
        entity.teacher = dto.getTeacher();
        switch (dto.getType()) {
            case LECTURE:
                entity.type = PairTypeEnumContract.LECTURE_TYPE_INDEX;
                break;
            case PRACTICE:
                entity.type = PairTypeEnumContract.PRACTICE_TYPE_INDEX;
                break;
            case LABORATORY:
                entity.type = PairTypeEnumContract.LABORATORY_TYPE_INDEX;
                break;
            case SPORT:
                entity.type = PairTypeEnumContract.SPORT_TYPE_INDEX;
                break;
            default:
                entity.type = PairTypeEnumContract.NOT_STATED_TYPE_INDEX;
        }
        entity.place = dto.getPlace();
        entity.additionalInfo = dto.getAdditionalInfo();
        return entity;
    }
}
