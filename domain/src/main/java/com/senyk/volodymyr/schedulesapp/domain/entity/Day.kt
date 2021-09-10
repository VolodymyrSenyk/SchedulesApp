package com.senyk.volodymyr.schedulesapp.domain.entity;

import java.util.List;

public class DayDto {
    private int ordinalNumber;
    private List<PairDto> pairs;

    public DayDto(int dayOrdinalNumber, List<PairDto> pairs) {
        this.ordinalNumber = dayOrdinalNumber;
        this.pairs = pairs;
    }

    public int getOrdinalNumber() {
        return this.ordinalNumber;
    }

    public List<PairDto> getPairs() {
        return this.pairs;
    }
}
