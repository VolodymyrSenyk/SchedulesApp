package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistupdater;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseUiListUpdater;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SchedulesUiListUpdater extends BaseUiListUpdater<ScheduleUi> {
    private final ResourcesProvider resourcesProvider;

    public SchedulesUiListUpdater(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public List<ScheduleUi> updateSelections(@NotNull List<ScheduleUi> oldList, String selectedScheduleName) {
        List<ScheduleUi> updatedList = new ArrayList<>(oldList.size());
        for (ScheduleUi item : oldList) {
            ScheduleUi updatedItem = new ScheduleUi(item);
            if (item.getName().equals(selectedScheduleName)) {
                updatedItem.setSelected(true);
            }
            updatedItem.setScheduleHolderColor(resourcesProvider.getScheduleHolderColor(updatedItem));
            updatedList.add(updatedItem);
        }
        return updatedList;
    }

    @Override
    public List<ScheduleUi> updateItemData(@NotNull List<ScheduleUi> oldList, ScheduleUi changedItemData) {
        List<ScheduleUi> updatedList = new ArrayList<>(oldList.size());
        for (ScheduleUi item : oldList) {
            if (item.getName().equals(changedItemData.getDateOfCreation())) {
                updatedList.add(changedItemData);
            } else {
                updatedList.add(item);
            }
        }
        return updatedList;
    }
}
