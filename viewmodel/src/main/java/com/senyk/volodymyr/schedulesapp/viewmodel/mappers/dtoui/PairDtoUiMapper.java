package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.PairsMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.Calendar;

public class PairDtoUiMapper extends BaseDtoUiMapper<PairDto, PairUi> {
    private static final int TIME_NOT_STATED_FLAG = 1980;
    private PairsMappingResourcesProvider resourcesProvider;

    public PairDtoUiMapper(PairsMappingResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    @Override
    public PairUi convertToUi(PairDto dto) {
        long time = dto.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String pairTime = "";
        if (calendar.get(Calendar.YEAR) != TIME_NOT_STATED_FLAG) {
            pairTime = resourcesProvider.getPairTime(time);
        }
        return new PairUi(
                pairTime,
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
        Long pairTime = uiModel.getTimeInMillis();
        if (pairTime == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(0L);
            calendar.set(Calendar.YEAR, TIME_NOT_STATED_FLAG);
            pairTime = calendar.getTimeInMillis();
        }
        return new PairDto(
                pairTime,
                uiModel.getName(),
                uiModel.getTeacher(),
                resourcesProvider.getPairType(uiModel.getType()),
                uiModel.getPlace(), uiModel.getAdditionalInfo());
    }

}
