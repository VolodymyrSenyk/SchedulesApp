package com.senyk.volodymyr.schedulesapp.viewmodel.models.ui;

public class PairUi {
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

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public int getHolderColor() {
        return holderColor;
    }

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
