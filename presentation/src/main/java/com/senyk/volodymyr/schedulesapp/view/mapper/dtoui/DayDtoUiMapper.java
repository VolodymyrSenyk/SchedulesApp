package com.senyk.volodymyr.schedulesapp.view.mapper.dtoui;

import com.senyk.volodymyr.schedulesapp.domain.entity.DayDto;
import com.senyk.volodymyr.schedulesapp.domain.entity.PairDto;
import com.senyk.volodymyr.schedulesapp.view.mapper.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.DayUi;
import com.senyk.volodymyr.schedulesapp.view.entity.PairUi;

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
