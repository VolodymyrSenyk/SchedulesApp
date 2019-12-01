package com.senyk.volodymyr.schedulesapp.model.models.dto;

import java.util.List;

public class DayDto {
    private int ordinalNumber;
    private List<PairDto> pairs;

    public int getOrdinalNumber() {
        return this.ordinalNumber;
    }

    public List<PairDto> getPairs() {
        return this.pairs;
    }

    public DayDto(int ordinalNumber, List<PairDto> pairs) {
        this.ordinalNumber = ordinalNumber;
        this.pairs = pairs;
    }
}
