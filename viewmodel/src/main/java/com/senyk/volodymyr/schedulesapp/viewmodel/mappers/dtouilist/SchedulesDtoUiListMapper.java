package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist;

import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SchedulesDtoUiListMapper extends BaseDtoUiListMapper<ScheduleDto, ScheduleUi> {
    private ResourcesProvider resourcesProvider;

    public SchedulesDtoUiListMapper(
            @NotNull BaseDtoUiMapper<ScheduleDto, ScheduleUi> mapper,
            ResourcesProvider resourcesProvider) {
        super(mapper);
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public List<ScheduleUi> convertToUi(@NotNull List<ScheduleDto> list) {
        List<ScheduleUi> convertedList = new ArrayList<>(list.size());
        for (ScheduleDto item : list) {
            ScheduleUi uiItem = listItemMapper.convertToUi(item);
            uiItem.setScheduleHolderColor(resourcesProvider.getScheduleHolderColor(uiItem));
            convertedList.add(uiItem);
        }
        if (!convertedList.isEmpty()) {
            ScheduleUi uiItem = convertedList.get(0);
            uiItem.setCurrent(true);
            uiItem.setSelected(true);
            uiItem.setScheduleHolderColor(resourcesProvider.getScheduleHolderColor(uiItem));
        }
        return convertedList;
    }

    public List<ScheduleUi> convertToUi(@NotNull List<ScheduleDto> list, String currentScheduleName) {
        List<ScheduleUi> convertedList = new ArrayList<>(list.size());
        for (ScheduleDto item : list) {
            ScheduleUi uiItem = listItemMapper.convertToUi(item);
            if (item.getName().equals(currentScheduleName)) {
                uiItem.setCurrent(true);
                uiItem.setSelected(true);
            }
            uiItem.setScheduleHolderColor(resourcesProvider.getScheduleHolderColor(uiItem));
            convertedList.add(uiItem);
        }
        return convertedList;
    }

    @Override
    public List<ScheduleDto> convertToDto(@NotNull List<ScheduleUi> list) {
        List<ScheduleDto> convertedList = new ArrayList<>(list.size());
        for (ScheduleUi item : list) {
            convertedList.add(listItemMapper.convertToDto(item));
        }
        return convertedList;
    }

}
