package com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

public class ScheduleNameInputField implements PrintableOnTheList {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
