package com.senyk.volodymyr.schedulesapp.view.mapper.dtoui;

import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule;
import com.senyk.volodymyr.schedulesapp.domain.helpers.resourcesproviders.SchedulesMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.view.entity.ScheduleUi;
import com.senyk.volodymyr.schedulesapp.view.mapper.base.BaseDtoUiMapper;

public class ScheduleDtoUiMapper extends BaseDtoUiMapper<Schedule, ScheduleUi> {
    public static final int NORMAL_WEEK_LENGTH = 5;
    public static final int WEEK_LENGTH_WITH_SAT = 6;

    public static final int NORMAL_NUM_OF_WEEK_TYPES = 1;
    public static final int NUM_OF_WEEK_TYPES_FOR_NUM_DENOM_SYSTEM = 2;

    private SchedulesMappingResourcesProvider resourcesProvider;

    public ScheduleDtoUiMapper(SchedulesMappingResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public ScheduleUi convertToUi(Schedule dto) {
        return new ScheduleUi(
                dto.getName(),
                resourcesProvider.getDateOfCreation(dto.getDateOfCreation()),
                dto.getNumberOfDays() == WEEK_LENGTH_WITH_SAT,
                dto.getNumberOfWeeks() == NUM_OF_WEEK_TYPES_FOR_NUM_DENOM_SYSTEM);
    }

    @Override
    public Schedule convertToDto(ScheduleUi uiModel) {
        return new Schedule(
                uiModel.getName(),
                System.currentTimeMillis(),
                uiModel.isSaturdayWorking() ? WEEK_LENGTH_WITH_SAT : NORMAL_WEEK_LENGTH,
                uiModel.isNumDenomSystem() ? NUM_OF_WEEK_TYPES_FOR_NUM_DENOM_SYSTEM : NORMAL_NUM_OF_WEEK_TYPES);
    }
}
