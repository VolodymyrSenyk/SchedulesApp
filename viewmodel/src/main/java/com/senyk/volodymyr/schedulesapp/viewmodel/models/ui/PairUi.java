package com.senyk.volodymyr.schedulesapp.viewmodel.models.ui;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

public class PairUi implements PrintableOnTheList {
    private String time;
    private String name;
    private String teacher;
    private String type;
    private String place;
    private String additionalInfo;
    private int holderColor;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public int getHolderColor() {
        return holderColor;
    }

    public void setHolderColor(int holderColor) {
        this.holderColor = holderColor;
    }

    public PairUi() {}

    public PairUi(String time, String name, String teacher, String type, String place, String additionalInfo, int holderColor) {
        this.time = time;
        this.name = name;
        this.teacher = teacher;
        this.type = type;
        this.place = place;
        this.additionalInfo = additionalInfo;
        this.holderColor = holderColor;
    }

}
