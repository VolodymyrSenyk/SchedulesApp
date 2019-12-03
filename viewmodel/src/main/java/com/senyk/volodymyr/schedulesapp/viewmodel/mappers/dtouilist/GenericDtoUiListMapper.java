package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist;

import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GenericDtoUiListMapper<DTO, UI> extends BaseDtoUiListMapper<DTO, UI> {
    public GenericDtoUiListMapper(@NotNull BaseDtoUiMapper<DTO, UI> mapper) {
        super(mapper);
    }

    @Override
    public List<UI> convertToUi(@NotNull List<DTO> list) {
        List<UI> convertedList = new ArrayList<>(list.size());
        for (DTO item : list) {
            convertedList.add(this.listItemMapper.convertToUi(item));
        }
        return convertedList;
    }

    @Override
    public List<DTO> convertToDto(@NotNull List<UI> list) {
        List<DTO> convertedList = new ArrayList<>(list.size());
        for (UI item : list) {
            convertedList.add(this.listItemMapper.convertToDto(item));
        }
        return convertedList;
    }
}
