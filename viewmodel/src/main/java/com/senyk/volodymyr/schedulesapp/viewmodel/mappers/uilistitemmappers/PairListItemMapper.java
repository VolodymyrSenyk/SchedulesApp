package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistitemmappers;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseUiListitemMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.pairs.creation.PairCreationItem;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

public class PairListItemMapper extends BaseUiListitemMapper<PairUi, PairCreationItem> {
    private ResourcesProvider resourcesProvider;

    public PairListItemMapper(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public PairCreationItem convertToListItem(PairUi uiModel) {
        return new PairCreationItem(
                uiModel.getTime(),
                uiModel.getName(),
                uiModel.getTeacher(),
                resourcesProvider.getPairTypeIndex(uiModel.getType()),
                uiModel.getPlace(),
                uiModel.getAdditionalInfo()
        );
    }

    @Override
    public PairUi convertToUiModel(PairCreationItem listItem) {
        return new PairUi(
                listItem.getTime(),
                listItem.getName(),
                listItem.getTeacher(),
                resourcesProvider.getPairTypeByIndex(listItem.getType()),
                listItem.getPlace(),
                listItem.getAdditionalInfo(),
                resourcesProvider.getPairsHolderColorByIndex(listItem.getType())
        );
    }
}
