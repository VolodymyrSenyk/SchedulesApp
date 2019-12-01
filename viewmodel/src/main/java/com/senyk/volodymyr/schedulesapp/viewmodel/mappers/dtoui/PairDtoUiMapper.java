package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

public class PairDtoUiMapper extends BaseDtoUiMapper<PairDto, PairUi> {
    private ResourcesProvider resourcesProvider;

    public PairDtoUiMapper(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public PairUi convertToUi(PairDto dto) {
        return new PairUi(
                resourcesProvider.getPairTime(dto.getTime()),
                dto.getTime(),
                dto.getName() != null ? dto.getName() : "",
                dto.getTeacher() != null ? dto.getTeacher() : "",
                resourcesProvider.getPairType(dto.getType()),
                dto.getPlace() != null ? dto.getPlace() : "",
                dto.getAdditionalInfo() != null ? dto.getAdditionalInfo() : "",
                resourcesProvider.getPairsHolderColor(dto.getType())
        );
    }

    @Override
    public PairDto convertToDto(PairUi uiModel) {
        return PairDto.getBuilder()
                .setTime(uiModel.getTimeInMillis())
                .setName(uiModel.getName())
                .setTeacher(uiModel.getTeacher())
                .setType(resourcesProvider.getPairType(uiModel.getType()))
                .setPlace(uiModel.getPlace())
                .setAdditionalInfo(uiModel.getAdditionalInfo())
                .build();
    }

}
