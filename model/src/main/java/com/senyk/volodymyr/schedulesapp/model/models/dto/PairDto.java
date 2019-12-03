package com.senyk.volodymyr.schedulesapp.model.models.dto;

import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;

public class PairDto {
    private long time;
    private String name;
    private String teacher;
    private PairType type;
    private String place;
    private String additionalInfo;

    public PairDto(long time, String name, String teacher, PairType type, String place, String additionalInfo) {
        this.time = time;
        this.name = name;
        this.teacher = teacher;
        this.type = type;
        this.place = place;
        this.additionalInfo = additionalInfo;
    }

    public long getTime() {
        return this.time;
    }

    public String getName() {
        return this.name;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public PairType getType() {
        return this.type;
    }

    public String getPlace() {
        return this.place;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }
}
