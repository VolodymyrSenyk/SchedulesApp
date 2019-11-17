package com.senyk.volodymyr.schedulesapp.model.models.dto;

public class ScheduleDto {
    private int id;
    private String name;
    private long createdAt;
    private int numberOfDays;
    private int numberOfWeeks;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getDataOfCreation() {
        return createdAt;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public ScheduleDto(int id, String name, long createdAt, int numberOfDays, int numberOfWeeks) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.numberOfDays = numberOfDays;
        this.numberOfWeeks = numberOfWeeks;
    }
}
