package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouimappers;

import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

public class ScheduleMapper extends BaseDtoUiMapper<ScheduleDto, ScheduleUi> {
    private ResourcesProvider resourcesProvider;

    public ScheduleMapper(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public ScheduleUi convertToUi(ScheduleDto dto) {
        ScheduleUi uiModel = new ScheduleUi(
                dto.getName(),
                resourcesProvider.getDateOfCreation(dto.getDateOfCreation()),
                dto.getNumberOfDays() == 6,
                dto.getNumberOfWeeks() == 2,
                false,
                false
        );
        uiModel.setScheduleHolderColor(resourcesProvider.getScheduleHolderColor(uiModel));
        return uiModel;
    }

    @Override
    public ScheduleDto convertToDto(ScheduleUi uiModel) {
        return new ScheduleDto(
            uiModel.getName(),
                resourcesProvider.getDateOfCreation(uiModel.getDateOfCreation()),
                uiModel.isSaturdayWorking() ? 6 : 5,
                uiModel.isNumDenomSystem() ? 2 : 1
        );
    }
}
