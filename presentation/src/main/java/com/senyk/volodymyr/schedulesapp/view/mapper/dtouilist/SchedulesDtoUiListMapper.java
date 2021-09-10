package com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist;

import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule;
import com.senyk.volodymyr.schedulesapp.view.mapper.base.BaseDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.ScheduleUi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SchedulesDtoUiListMapper extends BaseDtoUiListMapper<Schedule, ScheduleUi> {
    public SchedulesDtoUiListMapper(@NotNull BaseDtoUiMapper<Schedule, ScheduleUi> mapper) {
        super(mapper);
    }

    @Override
    public List<ScheduleUi> convertToUi(@NotNull List<Schedule> list) {
        List<ScheduleUi> convertedList = new ArrayList<>(list.size());
        for (Schedule item : list) {
            ScheduleUi uiItem = this.listItemMapper.convertToUi(item);
            convertedList.add(uiItem);
        }
        if (!convertedList.isEmpty()) {
            ScheduleUi uiItem = convertedList.get(0);
            uiItem.setCurrent(true);
        }
        return convertedList;
    }

    public List<ScheduleUi> convertToUi(@NotNull List<Schedule> list, String currentScheduleName) {
        List<ScheduleUi> convertedList = new ArrayList<>(list.size());
        for (Schedule item : list) {
            ScheduleUi uiItem = this.listItemMapper.convertToUi(item);
            if (item.getName().equals(currentScheduleName)) {
                uiItem.setCurrent(true);
            }
            convertedList.add(uiItem);
        }
        return convertedList;
    }

    @Override
    public List<Schedule> convertToDto(@NotNull List<ScheduleUi> list) {
        List<Schedule> convertedList = new ArrayList<>(list.size());
        for (ScheduleUi item : list) {
            convertedList.add(this.listItemMapper.convertToDto(item));
        }
        return convertedList;
    }
}
