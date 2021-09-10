package com.senyk.volodymyr.schedulesapp.data.mapper.entitydtolist;

import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.base.BaseEntityDtoMapper;

import java.util.ArrayList;
import java.util.List;

public class GenericEntityDtoListMapper<ENTITY, DTO> extends BaseEntityDtoListMapper<ENTITY, DTO> {
    public GenericEntityDtoListMapper(BaseEntityDtoMapper<ENTITY, DTO> mapper) {
        super(mapper);
    }

    public List<ENTITY> convertToEntities(List<DTO> list) {
        List<ENTITY> convertedList = new ArrayList<>(list.size());
        for (DTO item : list) {
            convertedList.add(this.listItemMapper.convertToEntity(item));
        }
        return convertedList;
    }

    public List<DTO> convertToDtos(List<ENTITY> list) {
        List<DTO> convertedList = new ArrayList<>(list.size());
        for (ENTITY item : list) {
            convertedList.add(this.listItemMapper.convertToDto(item));
        }
        return convertedList;
    }
}
