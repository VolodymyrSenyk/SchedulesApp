package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouimappers;

import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DtoUiListMapper<DTO, UI> {
    private BaseDtoUiMapper<DTO, UI> mapper;

    public DtoUiListMapper(@NotNull BaseDtoUiMapper<DTO, UI> mapper) {
        this.mapper = mapper;
    }

    public List<UI> convert(@NotNull List<DTO> list) {
        List<UI> convertedList = new ArrayList<>(list.size());
        for (DTO item : list) {
            convertedList.add(mapper.convertToUi(item));
        }
        return convertedList;
    }

    public List<UI> convertWithComparator(@NotNull List<DTO> list, @NotNull Comparator<UI> comparator) {
        List<UI> convertedList = this.convert(list);
        Collections.sort(convertedList, comparator);
        return convertedList;
    }
}
