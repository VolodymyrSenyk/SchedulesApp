package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base;

public abstract class BaseUiListitemMapper<UI, LIST_ITEM> {
    public abstract LIST_ITEM convertToListItem(UI uiModel);

    public abstract UI convertToUiModel(LIST_ITEM listItem);
}
