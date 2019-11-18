package com.senyk.volodymyr.schedulesapp.model.models.dto;

import java.util.List;

public class WeekDto {
    private int ordinalNumber;
    private List<DayDto> days;

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public List<DayDto> getDays() {
        return days;
    }

    public WeekDto(int ordinalNumber, List<DayDto> days) {
        this.ordinalNumber = ordinalNumber;
        this.days = days;
    }
}
