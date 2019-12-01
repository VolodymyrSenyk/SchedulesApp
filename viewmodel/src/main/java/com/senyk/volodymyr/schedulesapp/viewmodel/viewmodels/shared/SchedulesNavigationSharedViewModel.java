package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared;

import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseViewModel;

public class SchedulesNavigationSharedViewModel extends BaseViewModel {
    private boolean isAppInit = true;
    private String currentScheduleName = "";
    private int currentDayIndex;
    private int currentWeekIndex;

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

    public int getCurrentDayIndex() {
        return this.currentDayIndex;
    }

    public void saveCurrentDayIndex(int currentDayIndex) {
        this.currentDayIndex = currentDayIndex;
    }

    public int getCurrentWeekIndex() {
        return this.currentWeekIndex;
    }

    public void saveCurrentWeekIndex(int currentWeekIndex) {
        this.currentWeekIndex = currentWeekIndex;
    }

    public SchedulesNavigationSharedViewModel() {
        super(null);
    }
}
