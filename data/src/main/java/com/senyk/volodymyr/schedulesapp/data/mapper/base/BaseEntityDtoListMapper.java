package com.senyk.volodymyr.schedulesapp.data.mapper.base;

import java.util.List;

public abstract class BaseEntityDtoListMapper<ENTITY, DTO> {
    protected final BaseEntityDtoMapper<ENTITY, DTO> listItemMapper;

    public BaseEntityDtoListMapper(BaseEntityDtoMapper<ENTITY, DTO> listItemMapper) {
        this.listItemMapper = listItemMapper;
    }

    public abstract List<ENTITY> convertToEntities(List<DTO> list);

    public abstract List<DTO> convertToDtos(List<ENTITY> list);
}
