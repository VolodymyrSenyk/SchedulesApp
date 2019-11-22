package com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.pairs.creation;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.PairCreation;

public class PairCreationItem implements PairCreation {
    private String time;
    private String name;
    private String teacher;
    private int type;
    private String place;
    private String additionalInfo;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public PairCreationItem(String time, String name, String teacher, int type, String place, String additionalInfo) {
        this.time = time;
        this.name = name;
        this.teacher = teacher;
        this.type = type;
        this.place = place;
        this.additionalInfo = additionalInfo;
    }
}
