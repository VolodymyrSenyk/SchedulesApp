package com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers;

import com.senyk.volodymyr.schedulesapp.model.mappers.base.BaseEntityDtoMapper;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoListMapper<ENTITY, DTO> {
    private BaseEntityDtoMapper<ENTITY, DTO> mapper;

    public EntityDtoListMapper(BaseEntityDtoMapper<ENTITY, DTO> mapper) {
        this.mapper = mapper;
    }

    public List<ENTITY> convertToEntities(List<DTO> list) {
        List<ENTITY> convertedList = new ArrayList<>(list.size());
        for (DTO item : list) {
            convertedList.add(mapper.convertToEntity(item));
        }
        return convertedList;
    }

    public List<DTO> convertToDtos(List<ENTITY> list) {
        List<DTO> convertedList = new ArrayList<>(list.size());
        for (ENTITY item : list) {
            convertedList.add(mapper.convertToDto(item));
        }
        return convertedList;
    }
}
