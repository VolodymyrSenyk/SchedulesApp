package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BaseListMapper<FROM, TO> {
    private BaseMapper<FROM, TO> mapper;

    public BaseListMapper(@NotNull BaseMapper<FROM, TO> mapper) {
        this.mapper = mapper;
    }

    public List<TO> convert(@NotNull List<FROM> list) {
        List<TO> convertedList = new ArrayList<>(list.size());
        for (FROM item : list) {
            convertedList.add(mapper.convertTo(item));
        }
        return convertedList;
    }

    public List<TO> convertWithComparator(@NotNull List<FROM> list, @NotNull Comparator<TO> comparator) {
        List<TO> convertedList = this.convert(list);
        Collections.sort(convertedList, comparator);
        return convertedList;
    }
}
