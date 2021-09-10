package com.senyk.volodymyr.schedulesapp.view.entity.datainputfields;

import com.senyk.volodymyr.schedulesapp.view.entity.PrintableOnTheList;

public class ScheduleIsSaturdayWorkingCheckField implements PrintableOnTheList {
    private boolean isChecked;

    public ScheduleIsSaturdayWorkingCheckField() {
        this.isChecked = false;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
