package com.senyk.volodymyr.schedulesapp.model.mappers.base;

import java.util.List;

public abstract class BaseEntityDtoListMapper<ENTITY, DTO> {
    protected final BaseEntityDtoMapper<ENTITY, DTO> listItemMapper;

    public BaseEntityDtoListMapper(BaseEntityDtoMapper<ENTITY, DTO> simpleMapper) {
        this.listItemMapper = simpleMapper;
    }

    abstract public List<ENTITY> convertToEntities(List<DTO> list);

    abstract public List<DTO> convertToDtos(List<ENTITY> list);
}
