package com.senyk.volodymyr.schedulesapp.model.models.dto;

public class ScheduleDto {
    private String name;
    private long createdAt;
    private int numberOfDays;
    private int numberOfWeeks;

    public String getName() {
        return name;
    }

    public long getDateOfCreation() {
        return createdAt;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public ScheduleDto(String name, int numberOfDays, int numberOfWeeks) {
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.numberOfWeeks = numberOfWeeks;
    }

    public ScheduleDto(String name, long createdAt, int numberOfDays, int numberOfWeeks) {
        this.name = name;
        this.createdAt = createdAt;
        this.numberOfDays = numberOfDays;
        this.numberOfWeeks = numberOfWeeks;
    }
}
