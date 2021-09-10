package com.senyk.volodymyr.schedulesapp.view.adapterdelegate.listener;

public interface SchedulesClickListener {
    void scheduleClicked(String clickedScheduleName);

    void scheduleDeleteClicked(String clickedScheduleName);
}
