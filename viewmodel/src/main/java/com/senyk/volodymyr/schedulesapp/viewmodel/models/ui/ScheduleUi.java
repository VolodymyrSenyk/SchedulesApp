package com.senyk.volodymyr.schedulesapp.viewmodel.models.ui;

public class ScheduleUi {
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

    public String getDateOfCreation() {
        return createdAt;
    }

    public boolean isSaturdayWorking() {
        return isSaturdayWorking;
    }

    public boolean isNumDenomSystem() {
        return isNumDenomSystem;
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

    public boolean isSelected() {
        return isSelected;
    }

    public ScheduleUi(
            String name,
            String createdAt,
            boolean isSaturdayWorking,
            boolean isNumDenomSystem,
            boolean isCurrent,
            boolean isSelected
    ) {
        this.name = name;
        this.createdAt = createdAt;
        this.isSaturdayWorking = isSaturdayWorking;
        this.isNumDenomSystem = isNumDenomSystem;
        this.isCurrent = isCurrent;
        this.isSelected = isSelected;
    }
}
