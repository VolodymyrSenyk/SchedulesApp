package com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

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
