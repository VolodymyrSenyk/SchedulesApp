package com.senyk.volodymyr.schedulesapp.model.models.dto;

import java.util.Calendar;

public class ScheduleDto {
    private String name;
    private long createdAt;
    private int numberOfDays;
    private int numberOfWeeks;

    public ScheduleDto(String name, int numberOfDays, int numberOfWeeks) {
        this(name, Calendar.getInstance().getTimeInMillis(), numberOfDays, numberOfWeeks);
    }

    public ScheduleDto(String name, long createdAt, int numberOfDays, int numberOfWeeks) {
        this.name = name;
        this.createdAt = createdAt;
        this.numberOfDays = numberOfDays;
        this.numberOfWeeks = numberOfWeeks;
    }

    public String getName() {
        return this.name;
    }

    public long getDateOfCreation() {
        return this.createdAt;
    }

    public int getNumberOfDays() {
        return this.numberOfDays;
    }

    public int getNumberOfWeeks() {
        return this.numberOfWeeks;
    }
}
