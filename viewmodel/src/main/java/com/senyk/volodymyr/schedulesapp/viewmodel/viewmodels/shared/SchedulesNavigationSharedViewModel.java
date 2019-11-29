package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseViewModel;

public class SchedulesNavigationSharedViewModel extends BaseViewModel {
    private boolean isAppInit = true;
    private String currentScheduleName = "";
    private int currentWeekIndexForPager = 0;

    private MutableLiveData<Pair<Integer, Integer>> currentScheduleIndexes = new MutableLiveData<>();

    {
        currentScheduleIndexes.setValue(new Pair<>(0, 0));
    }

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

    public int getCurrentWeekIndexForPager() {
        return this.currentWeekIndexForPager;
    }

    public void setCurrentWeekIndexForPager(int currentWeekIndexForPager) {
        this.currentWeekIndexForPager = currentWeekIndexForPager;
    }

    public LiveData<Pair<Integer, Integer>> getCurrentScheduleIndexes() {
        return this.currentScheduleIndexes;
    }

    public void setCurrentWeekIndex(int currentWeekIndex) {
        if (currentScheduleIndexes.getValue() != null) {
            this.currentScheduleIndexes.setValue(new Pair<>(currentWeekIndex, currentScheduleIndexes.getValue().second));
        } else {
            this.currentScheduleIndexes.setValue(new Pair<>(currentWeekIndex, 0));
        }
    }

    public void setCurrentDayIndex(int currentDayIndex) {
        if (currentScheduleIndexes.getValue() != null) {
            this.currentScheduleIndexes.setValue(new Pair<>(currentScheduleIndexes.getValue().first, currentDayIndex));
        } else {
            this.currentScheduleIndexes.setValue(new Pair<>(0, currentDayIndex));
        }
    }

    public SchedulesNavigationSharedViewModel() {
        super(null);
    }
}
