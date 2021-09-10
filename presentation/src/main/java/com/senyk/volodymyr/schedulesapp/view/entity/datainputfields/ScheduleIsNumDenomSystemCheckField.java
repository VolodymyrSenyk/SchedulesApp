package com.senyk.volodymyr.schedulesapp.view.entity.datainputfields;

import com.senyk.volodymyr.schedulesapp.view.entity.PrintableOnTheList;

public class ScheduleIsNumDenomSystemCheckField implements PrintableOnTheList {
    private boolean isChecked;

    public ScheduleIsNumDenomSystemCheckField() {
        this.isChecked = true;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
