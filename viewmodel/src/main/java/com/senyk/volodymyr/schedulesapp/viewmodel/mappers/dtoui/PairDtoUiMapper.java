package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.PairsMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

public class PairDtoUiMapper extends BaseDtoUiMapper<PairDto, PairUi> {
    private PairsMappingResourcesProvider resourcesProvider;

    public PairDtoUiMapper(PairsMappingResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public PairUi convertToUi(PairDto dto) {
        return new PairUi(
                resourcesProvider.getPairTime(dto.getTime()),
                dto.getTime(),
                dto.getName(),
                dto.getTeacher(),
                resourcesProvider.getPairType(dto.getType()),
                dto.getPlace(),
                dto.getAdditionalInfo(),
                resourcesProvider.getPairsHolderColor(dto.getType()));
    }

    @Override
    public PairDto convertToDto(PairUi uiModel) {
        return new PairDto(
                uiModel.getTimeInMillis(),
                uiModel.getName(),
                uiModel.getTeacher(),
                resourcesProvider.getPairType(uiModel.getType()),
                uiModel.getPlace(), uiModel.getAdditionalInfo());
    }

}
