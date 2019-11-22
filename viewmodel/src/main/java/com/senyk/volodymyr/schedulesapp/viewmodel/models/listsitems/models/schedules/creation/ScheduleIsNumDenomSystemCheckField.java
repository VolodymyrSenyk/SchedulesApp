package com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.schedules.creation;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.ScheduleCreationField;

public class ScheduleIsNumDenomSystemCheckField implements ScheduleCreationField {
    private boolean isChecked;

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public ScheduleIsNumDenomSystemCheckField() {
        this.isChecked = false;
    }
}
