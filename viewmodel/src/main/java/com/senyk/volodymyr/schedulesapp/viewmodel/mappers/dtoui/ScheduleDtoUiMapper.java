package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

public class ScheduleDtoUiMapper extends BaseDtoUiMapper<ScheduleDto, ScheduleUi> {
    public static final int NORMAL_WEEK_LENGTH = 5;
    public static final int WEEK_LENGTH_WITH_SAT = 6;

    public static final int NORMAL_NUM_OF_WEEK_TYPES = 1;
    public static final int NUM_OF_WEEK_TYPES_NUM_DENOM = 2;

    private ResourcesProvider resourcesProvider;

    public ScheduleDtoUiMapper(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public ScheduleUi convertToUi(ScheduleDto dto) {
        return new ScheduleUi(
                dto.getName(),
                resourcesProvider.getDateTime(dto.getDateOfCreation()),
                dto.getNumberOfDays() == WEEK_LENGTH_WITH_SAT,
                dto.getNumberOfWeeks() == NUM_OF_WEEK_TYPES_NUM_DENOM
        );
    }

    @Override
    public ScheduleDto convertToDto(ScheduleUi uiModel) {
        return new ScheduleDto(
                uiModel.getName(),
                uiModel.isSaturdayWorking() ? WEEK_LENGTH_WITH_SAT : NORMAL_WEEK_LENGTH,
                uiModel.isNumDenomSystem() ? NUM_OF_WEEK_TYPES_NUM_DENOM : NORMAL_NUM_OF_WEEK_TYPES
        );
    }
}
