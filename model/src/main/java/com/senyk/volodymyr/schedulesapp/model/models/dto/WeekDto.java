package com.senyk.volodymyr.schedulesapp.model.models.dto;

import java.util.List;

public class WeekDto {
    private int ordinalNumber;
    private List<DayDto> days;

    public int getOrdinalNumber() {
        return this.ordinalNumber;
    }

    public List<DayDto> getDays() {
        return this.days;
    }

    public WeekDto(int ordinalNumber, List<DayDto> days) {
        this.ordinalNumber = ordinalNumber;
        this.days = days;
    }
}
