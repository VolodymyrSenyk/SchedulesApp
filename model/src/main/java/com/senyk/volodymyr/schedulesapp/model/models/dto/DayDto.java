package com.senyk.volodymyr.schedulesapp.model.models.dto;

import java.util.List;

public class DayDto {
    private int id;
    private int dayOrdinal;
    private List<PairDto> pairs;

    public int getId() {
        return id;
    }

    public int getDayOrdinal() {
        return dayOrdinal;
    }

    public List<PairDto> getPairs() {
        return pairs;
    }

    public DayDto(int id, int dayOrdinal, List<PairDto> pairs) {
        this.id = id;
        this.dayOrdinal = dayOrdinal;
        this.pairs = pairs;
    }
}
