package com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.schedules.creation;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.ScheduleCreationField;

public class ScheduleIsSaturdayWorkingCheckField implements ScheduleCreationField {
    private boolean isChecked;

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public ScheduleIsSaturdayWorkingCheckField() {
        this.isChecked = false;
    }
}
