package com.senyk.volodymyr.schedulesapp.model.models.dto;

import java.util.List;

public class DayDto {
    private int ordinalNumber;
    private List<PairDto> pairs;

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public List<PairDto> getPairs() {
        return pairs;
    }

    public DayDto(int ordinalNumber, List<PairDto> pairs) {
        this.ordinalNumber = ordinalNumber;
        this.pairs = pairs;
    }
}
