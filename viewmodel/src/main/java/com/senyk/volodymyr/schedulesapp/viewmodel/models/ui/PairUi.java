package com.senyk.volodymyr.schedulesapp.viewmodel.models.ui;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

public class PairUi implements PrintableOnTheList {
    private String time;
    private long timeInMillis;
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

    public long getTimeInMillis() {
        return this.timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
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

    public PairUi() {
        this("", 0, "", "", "", "", "", 0);
    }

    public PairUi(String time, long timeInMillis, String name, String teacher, String type, String place, String additionalInfo, int holderColor) {
        this.time = time;
        this.timeInMillis = timeInMillis;
        this.name = name;
        this.teacher = teacher;
        this.type = type;
        this.place = place;
        this.additionalInfo = additionalInfo;
        this.holderColor = holderColor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.time.hashCode();
        result = prime * result + this.name.hashCode();
        result = prime * result + this.teacher.hashCode();
        result = prime * result + this.type.hashCode();
        result = prime * result + this.place.hashCode();
        result = prime * result + this.additionalInfo.hashCode();
        result = prime * result + this.holderColor;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        if (this.hashCode() != obj.hashCode()) return false;
        PairUi other = (PairUi) obj;
        return this.time.equals(other.time) &&
                this.name.equals(other.name) &&
                this.teacher.equals(other.teacher) &&
                this.type.equals(other.type) &&
                this.place.equals(other.place) &&
                this.additionalInfo.equals(other.additionalInfo) &&
                this.holderColor == other.holderColor;
    }
}
