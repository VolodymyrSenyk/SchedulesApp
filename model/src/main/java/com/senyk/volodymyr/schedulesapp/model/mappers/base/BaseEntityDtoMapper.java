package com.senyk.volodymyr.schedulesapp.model.mappers.base;

public abstract class BaseEntityDtoMapper<ENTITY, DTO> {
    public abstract DTO convertToDto(ENTITY entity);

    public abstract ENTITY convertToEntity(DTO dto);
}
