package com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.schedules.output;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.ScheduleOutput;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

public class ScheduleOutputItem extends ScheduleUi implements ScheduleOutput {
    public ScheduleOutputItem(ScheduleUi schedule) {
        super(
                schedule.getName(),
                schedule.getDateOfCreation(),
                schedule.isSaturdayWorking(),
                schedule.isNumDenomSystem(),
                schedule.isCurrent(),
                schedule.isSelected()
        );
    }
}
