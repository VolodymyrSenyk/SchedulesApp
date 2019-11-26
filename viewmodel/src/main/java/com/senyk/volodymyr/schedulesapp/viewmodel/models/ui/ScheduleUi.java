package com.senyk.volodymyr.schedulesapp.viewmodel.models.ui;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

public class ScheduleUi implements PrintableOnTheList {
    private String name;
    private String createdAt;
    private boolean isSaturdayWorking;
    private boolean isNumDenomSystem;
    private int scheduleHolderColor;
    private boolean isCurrent;
    private boolean isSelected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfCreation() {
        return createdAt;
    }

    public boolean isSaturdayWorking() {
        return isSaturdayWorking;
    }

    public void setSaturdayWorking(boolean saturdayWorking) {
        isSaturdayWorking = saturdayWorking;
    }

    public boolean isNumDenomSystem() {
        return isNumDenomSystem;
    }

    public void setNumDenomSystem(boolean numDenomSystem) {
        isNumDenomSystem = numDenomSystem;
    }

    public void setScheduleHolderColor(int scheduleHolderColor) {
        this.scheduleHolderColor = scheduleHolderColor;
    }

    public int getScheduleHolderColor() {
        return scheduleHolderColor;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public ScheduleUi() {}

    public ScheduleUi(
            String name,
            String createdAt,
            boolean isSaturdayWorking,
            boolean isNumDenomSystem
    ) {
        this.name = name;
        this.createdAt = createdAt;
        this.isSaturdayWorking = isSaturdayWorking;
        this.isNumDenomSystem = isNumDenomSystem;
    }

    public ScheduleUi(ScheduleUi schedule) {
        this.name = schedule.getName();
        this.createdAt = schedule.getDateOfCreation();
        this.isSaturdayWorking = schedule.isSaturdayWorking();
        this.isNumDenomSystem = schedule.isNumDenomSystem();
        this.isCurrent = schedule.isCurrent();
    }
}
