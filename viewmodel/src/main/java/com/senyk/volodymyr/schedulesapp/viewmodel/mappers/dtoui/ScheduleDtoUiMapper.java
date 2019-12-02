package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.SchedulesMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

public class ScheduleDtoUiMapper extends BaseDtoUiMapper<ScheduleDto, ScheduleUi> {
    public static final int NORMAL_WEEK_LENGTH = 5;
    public static final int WEEK_LENGTH_WITH_SAT = 6;

    public static final int NORMAL_NUM_OF_WEEK_TYPES = 1;
    public static final int NUM_OF_WEEK_TYPES_FOR_NUM_DENOM_SYSTEM = 2;

    private SchedulesMappingResourcesProvider resourcesProvider;

    public ScheduleDtoUiMapper(SchedulesMappingResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public ScheduleUi convertToUi(ScheduleDto dto) {
        return new ScheduleUi(
                dto.getName(),
                resourcesProvider.getDateOfCreation(dto.getDateOfCreation()),
                dto.getNumberOfDays() == WEEK_LENGTH_WITH_SAT,
                dto.getNumberOfWeeks() == NUM_OF_WEEK_TYPES_FOR_NUM_DENOM_SYSTEM);
    }

    @Override
    public ScheduleDto convertToDto(ScheduleUi uiModel) {
        return new ScheduleDto(
                uiModel.getName(),
                uiModel.isSaturdayWorking() ? WEEK_LENGTH_WITH_SAT : NORMAL_WEEK_LENGTH,
                uiModel.isNumDenomSystem() ? NUM_OF_WEEK_TYPES_FOR_NUM_DENOM_SYSTEM : NORMAL_NUM_OF_WEEK_TYPES);
    }
}
