package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.DayDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.DayUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

public class DayDtoUiMapper extends BaseDtoUiMapper<DayDto, DayUi> {
    private GenericDtoUiListMapper<PairDto, PairUi> pairsMapper;

    public DayDtoUiMapper(GenericDtoUiListMapper<PairDto, PairUi> pairsMapper) {
        this.pairsMapper = pairsMapper;
    }

    @Override
    public DayUi convertToUi(DayDto dto) {
        return new DayUi(
                dto.getOrdinalNumber(),
                -1,
                this.pairsMapper.convertToUi(dto.getPairs()));
    }

    @Override
    public DayDto convertToDto(DayUi uiModel) {
        return new DayDto(
                uiModel.getOrdinal(),
                this.pairsMapper.convertToDto(uiModel.getPairs()));
    }

}
