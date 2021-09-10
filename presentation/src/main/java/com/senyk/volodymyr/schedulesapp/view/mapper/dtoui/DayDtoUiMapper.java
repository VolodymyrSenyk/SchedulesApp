package com.senyk.volodymyr.schedulesapp.view.mapper.dtoui;

import com.senyk.volodymyr.schedulesapp.domain.entity.Day;
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair;
import com.senyk.volodymyr.schedulesapp.view.mapper.base.BaseDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.DayUi;
import com.senyk.volodymyr.schedulesapp.view.entity.PairUi;

public class DayDtoUiMapper extends BaseDtoUiMapper<Day, DayUi> {
    private GenericDtoUiListMapper<Pair, PairUi> pairsMapper;

    public DayDtoUiMapper(GenericDtoUiListMapper<Pair, PairUi> pairsMapper) {
        this.pairsMapper = pairsMapper;
    }

    @Override
    public DayUi convertToUi(Day dto) {
        return new DayUi(
                dto.getOrdinalNumber(),
                -1,
                this.pairsMapper.convertToUi(dto.getPairs()));
    }

    @Override
    public Day convertToDto(DayUi uiModel) {
        return new Day(
                uiModel.getOrdinal(),
                this.pairsMapper.convertToDto(uiModel.getPairs()));
    }

}
