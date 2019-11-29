package com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.base.BaseViewModel;

import java.util.Calendar;

public class SchedulesNavigationSharedViewModel extends BaseViewModel {
    private boolean isAppInit = true;
    private String currentScheduleName = "";

    private MutableLiveData<Pair<Integer, Integer>> currentScheduleIndexes = new MutableLiveData<>();

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

    public int initNavigationIndexes(int daysCount) {
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int currentDayNumber = currentDay == 1 ? 7 : currentDay - 1;
        if (currentDayNumber > daysCount) {
            setCurrentDayIndex(0);
        } else {
            setCurrentDayIndex(currentDayNumber - 1);
        }
        if (currentScheduleIndexes.getValue() != null) {
            return currentScheduleIndexes.getValue().second;
        } else {
            return 0;
        }
    }

    public SchedulesNavigationSharedViewModel() {
        super(null);
    }
}
