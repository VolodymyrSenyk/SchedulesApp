package com.senyk.volodymyr.schedulesapp.viewmodel.models.ui;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

public class ScheduleUi implements PrintableOnTheList {
    private String name;
    private String createdAt;
    private boolean isSaturdayWorking;
    private boolean isNumDenomSystem;
    private boolean isCurrent;

    public ScheduleUi() {
        this("", "", false, true);
    }

    public ScheduleUi(String name, boolean isSaturdayWorking, boolean isNumDenomSystem) {
        this(name, "", isSaturdayWorking, isNumDenomSystem);
    }

    public ScheduleUi(
            String name,
            String createdAt,
            boolean isSaturdayWorking,
            boolean isNumDenomSystem) {
        this.name = name;
        this.createdAt = createdAt;
        this.isSaturdayWorking = isSaturdayWorking;
        this.isNumDenomSystem = isNumDenomSystem;
    }

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

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.name.hashCode();
        result = prime * result + this.createdAt.hashCode();
        result = prime * result + Boolean.valueOf(this.isSaturdayWorking).hashCode();
        result = prime * result + Boolean.valueOf(this.isNumDenomSystem).hashCode();
        result = prime * result + Boolean.valueOf(this.isCurrent).hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        if (this.hashCode() != obj.hashCode()) return false;
        ScheduleUi other = (ScheduleUi) obj;
        return this.name.equals(other.name) &&
                this.createdAt.equals(other.createdAt) &&
                this.isSaturdayWorking == other.isSaturdayWorking &&
                this.isNumDenomSystem == other.isNumDenomSystem &&
                this.isCurrent == other.isCurrent;
    }
}
