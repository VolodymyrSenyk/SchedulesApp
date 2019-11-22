package com.senyk.volodymyr.schedulesapp.viewmodel.helpers;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;
import com.senyk.volodymyr.schedulesapp.viewmodel.R;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import java.util.Calendar;

public class ResourcesProvider {
    private Context context;

    public ResourcesProvider(Context context) {
        this.context = context;
    }

    public String getErrorMessage(String errorMessage) {
        return context.getString(R.string.error_message, errorMessage);
    }

    public String getPairTime(long time) {
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTimeInMillis(time);
        return context.getString(
                R.string.pair_time_output,
                timeCalendar.get(Calendar.HOUR_OF_DAY),
                timeCalendar.get(Calendar.MINUTE)
        );
    }

    public long getPairTime(String time) {
        String[] timeArray = time.split("[:]");
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        timeCalendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        return timeCalendar.getTimeInMillis();
    }

    public String getPairType(PairType type) {
        String[] allTypes = context.getResources().getStringArray(R.array.pairs_types);
        switch (type) {
            case LECTURE:
                return allTypes[1];
            case PRACTICE:
                return allTypes[2];
            case LABORATORY:
                return allTypes[3];
            case SPORT:
                return allTypes[4];
            default:
                return allTypes[0];
        }
    }

    public PairType getPairType(String type) {
        String[] allTypes = context.getResources().getStringArray(R.array.pairs_types);
        if (type.equals(allTypes[1])) {
            return PairType.LECTURE;
        } else if (type.equals(allTypes[2])) {
            return PairType.PRACTICE;
        } else if (type.equals(allTypes[3])) {
            return PairType.LABORATORY;
        } else if (type.equals(allTypes[4])) {
            return PairType.SPORT;
        } else {
            return PairType.NOT_STATED;
        }
    }

    public int getPairsHolderColor(PairType type) {
        switch (type) {
            case LECTURE:
                return ContextCompat.getColor(context, R.color.colorLecture);
            case PRACTICE:
                return ContextCompat.getColor(context, R.color.colorPractice);
            case LABORATORY:
                return ContextCompat.getColor(context, R.color.colorLaboratory);
            case SPORT:
                return ContextCompat.getColor(context, R.color.colorSport);
            default:
                return ContextCompat.getColor(context, R.color.colorNotStated);
        }
    }

    public String getDateOfCreation(long date) {
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTimeInMillis(date);
        return context.getString(
                R.string.schedule_date_of_creation_output,
                timeCalendar.get(Calendar.DAY_OF_MONTH),
                timeCalendar.get(Calendar.MONTH),
                timeCalendar.get(Calendar.YEAR),
                timeCalendar.get(Calendar.HOUR_OF_DAY),
                timeCalendar.get(Calendar.MINUTE)
        );
    }

    public long getDateOfCreation(String date) {
        String[] dateTimeArray = date.split("[ ]");
        String[] dateArray = dateTimeArray[0].split("[.]");
        String[] timeArray = dateTimeArray[1].split("[:]");
        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[0]));
        timeCalendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]));
        timeCalendar.set(Calendar.YEAR, Integer.parseInt(dateArray[2]));
        timeCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        timeCalendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        return timeCalendar.getTimeInMillis();
    }

    public int getScheduleHolderColor(ScheduleUi schedule) {
        if (schedule.isCurrent() && schedule.isSelected()) {
            return ContextCompat.getColor(context, R.color.colorCurrentSelectedSchedule);
        } else if (schedule.isCurrent() && !schedule.isSelected()) {
            return ContextCompat.getColor(context, R.color.colorCurrentDeselectedSchedule);
        } else if (!schedule.isCurrent() && schedule.isSelected()) {
            return ContextCompat.getColor(context, R.color.colorSelectedSchedule);
        } else if (!schedule.isCurrent() && !schedule.isSelected()) {
            return ContextCompat.getColor(context, R.color.colorDeselectedSchedule);
        } else {
            return ContextCompat.getColor(context, R.color.colorDeselectedSchedule);
        }
    }

}
