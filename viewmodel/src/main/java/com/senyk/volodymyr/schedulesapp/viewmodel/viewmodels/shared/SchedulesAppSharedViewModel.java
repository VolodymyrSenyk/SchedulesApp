package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared;

import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseViewModel;

public class SchedulesAppSharedViewModel extends BaseViewModel {
    private boolean isAppInit = true;
    private String currentScheduleName = "";

    public boolean isAppInit() {
        return this.isAppInit;
    }

    public void setAppInitFinished() {
        this.isAppInit = false;
    }

    public String getCurrentScheduleName() {
        return currentScheduleName;
    }

    public void setCurrentScheduleName(String newCurrentScheduleName) {
        this.currentScheduleName = newCurrentScheduleName;
    }

    public SchedulesAppSharedViewModel() {
        super(null);
    }
}
