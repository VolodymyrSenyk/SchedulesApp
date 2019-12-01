package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared;

import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseViewModel;

public class SchedulesNavigationSharedViewModel extends BaseViewModel {
    private boolean isAppInit = true;
    private String currentScheduleName = "";

    public boolean isAppInit() {
        return this.isAppInit;
    }

    public void setAppInitFinished() {
        this.isAppInit = false;
    }

    public String getCurrentScheduleName() {
        return this.currentScheduleName;
    }

    public void setCurrentScheduleName(String newCurrentScheduleName) {
        this.currentScheduleName = newCurrentScheduleName;
    }

    public SchedulesNavigationSharedViewModel() {
        super(null);
    }
}
