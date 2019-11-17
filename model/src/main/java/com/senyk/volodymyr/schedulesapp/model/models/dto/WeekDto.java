package com.senyk.volodymyr.schedulesapp.model.models.dto;

import java.util.List;

public class WeekDto {
    private int id;
    private int number;
    private List<DayDto> days;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public List<DayDto> getDays() {
        return days;
    }

    public WeekDto(int id, int number, List<DayDto> days) {
        this.id = id;
        this.number = number;
        this.days = days;
    }
}
