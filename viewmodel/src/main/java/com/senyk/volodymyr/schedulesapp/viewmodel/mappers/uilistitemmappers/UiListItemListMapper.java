package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistitemmappers;

import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseUiListitemMapper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UiListItemListMapper<UI, LIST_ITEM> {
    private BaseUiListitemMapper<UI, LIST_ITEM> mapper;

    public UiListItemListMapper(@NotNull BaseUiListitemMapper<UI, LIST_ITEM> mapper) {
        this.mapper = mapper;
    }

    public List<LIST_ITEM> convertToListItems(@NotNull List<UI> list) {
        List<LIST_ITEM> convertedList = new ArrayList<>(list.size());
        for (UI item : list) {
            convertedList.add(mapper.convertToListItem(item));
        }
        return convertedList;
    }

    public List<LIST_ITEM> convertToUiModels(@NotNull List<UI> list) {
        List<LIST_ITEM> convertedList = new ArrayList<>(list.size());
        for (UI item : list) {
            convertedList.add(mapper.convertToListItem(item));
        }
        return convertedList;
    }

    public List<LIST_ITEM> convertToUiModelsWithComparator(@NotNull List<UI> list, @NotNull Comparator<LIST_ITEM> comparator) {
        List<LIST_ITEM> convertedList = this.convertToUiModels(list);
        Collections.sort(convertedList, comparator);
        return convertedList;
    }
}
