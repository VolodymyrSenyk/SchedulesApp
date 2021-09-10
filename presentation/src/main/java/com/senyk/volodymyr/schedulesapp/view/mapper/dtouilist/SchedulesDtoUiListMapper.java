package com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist;

import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.view.mapper.base.BaseDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.ScheduleUi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SchedulesDtoUiListMapper extends BaseDtoUiListMapper<ScheduleDto, ScheduleUi> {
    public SchedulesDtoUiListMapper(@NotNull BaseDtoUiMapper<ScheduleDto, ScheduleUi> mapper) {
        super(mapper);
    }

    @Override
    public List<ScheduleUi> convertToUi(@NotNull List<ScheduleDto> list) {
        List<ScheduleUi> convertedList = new ArrayList<>(list.size());
        for (ScheduleDto item : list) {
            ScheduleUi uiItem = this.listItemMapper.convertToUi(item);
            convertedList.add(uiItem);
        }
        if (!convertedList.isEmpty()) {
            ScheduleUi uiItem = convertedList.get(0);
            uiItem.setCurrent(true);
        }
        return convertedList;
    }

    public List<ScheduleUi> convertToUi(@NotNull List<ScheduleDto> list, String currentScheduleName) {
        List<ScheduleUi> convertedList = new ArrayList<>(list.size());
        for (ScheduleDto item : list) {
            ScheduleUi uiItem = this.listItemMapper.convertToUi(item);
            if (item.getName().equals(currentScheduleName)) {
                uiItem.setCurrent(true);
            }
            convertedList.add(uiItem);
        }
        return convertedList;
    }

    @Override
    public List<ScheduleDto> convertToDto(@NotNull List<ScheduleUi> list) {
        List<ScheduleDto> convertedList = new ArrayList<>(list.size());
        for (ScheduleUi item : list) {
            convertedList.add(this.listItemMapper.convertToDto(item));
        }
        return convertedList;
    }
}
