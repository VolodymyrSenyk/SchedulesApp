package com.senyk.volodymyr.schedulesapp.view.entity.datainputfields;

import com.senyk.volodymyr.schedulesapp.view.entity.PrintableOnTheList;

public class ScheduleNameInputField implements PrintableOnTheList {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
