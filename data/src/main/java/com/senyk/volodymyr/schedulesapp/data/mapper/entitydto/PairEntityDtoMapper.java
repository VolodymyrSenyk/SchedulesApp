package com.senyk.volodymyr.schedulesapp.data.mapper.entitydto;

import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair;
import com.senyk.volodymyr.schedulesapp.domain.entity.PairType;

public class PairEntityDtoMapper extends BaseEntityDtoMapper<PairDataEntity, Pair> {
    @Override
    public Pair convertToDto(PairDataEntity entity) {
        PairType pairType;
        switch (entity.type) {
            case PairType.LECTURE_TYPE_INDEX:
                pairType = PairType.LECTURE;
                break;
            case PairType.PRACTICE_TYPE_INDEX:
                pairType = PairType.PRACTICE;
                break;
            case PairType.LABORATORY_TYPE_INDEX:
                pairType = PairType.LABORATORY;
                break;
            case PairType.SPORT_TYPE_INDEX:
                pairType = PairType.SPORT;
                break;
            default:
                pairType = PairType.NOT_STATED;
        }
        return new Pair(entity.time, entity.name, entity.teacher, pairType, entity.place, entity.additionalInfo);
    }

    @Override
    public PairDataEntity convertToEntity(Pair dto) {
        PairDataEntity entity = new PairDataEntity();
        entity.name = dto.getName();
        entity.time = dto.getTime();
        entity.teacher = dto.getTeacher();
        switch (dto.getType()) {
            case LECTURE:
                entity.type = PairType.LECTURE_TYPE_INDEX;
                break;
            case PRACTICE:
                entity.type = PairType.PRACTICE_TYPE_INDEX;
                break;
            case LABORATORY:
                entity.type = PairType.LABORATORY_TYPE_INDEX;
                break;
            case SPORT:
                entity.type = PairType.SPORT_TYPE_INDEX;
                break;
            default:
                entity.type = PairType.NOT_STATED_TYPE_INDEX;
        }
        entity.place = dto.getPlace();
        entity.additionalInfo = dto.getAdditionalInfo();
        return entity;
    }
}
