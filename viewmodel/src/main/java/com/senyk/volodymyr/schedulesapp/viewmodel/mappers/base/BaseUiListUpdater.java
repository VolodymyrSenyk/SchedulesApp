package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUiListUpdater<UI> {
    public List<UI> convertToNewList(@NotNull List<UI> oldList) {
        List<UI> newList = new ArrayList<>(oldList.size());
        newList.addAll(oldList);
        return newList;
    }

    public abstract List<UI> updateSelections(@NotNull List<UI> oldList, String selectedItemId);

    public abstract List<UI> updateItemData(@NotNull List<UI> oldList, UI changedItemData);
}
