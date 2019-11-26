package com.senyk.volodymyr.schedulesapp.viewmodel.helpers;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;
import com.senyk.volodymyr.schedulesapp.model.models.enums.PairTypeEnumContract;
import com.senyk.volodymyr.schedulesapp.viewmodel.R;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import java.util.Calendar;

public class ResourcesProvider {
    private final Context context;

    public ResourcesProvider(Context context) {
        this.context = context;
    }

    public String getErrorMessage(String errorMessage) {
        return context.getString(R.string.error_message, errorMessage);
    }

    public String getFieldLimitReachedErrorMessage() {
        return context.getString(R.string.field_limit_reached_error_message);
    }

    public String getScheduleExistsErrorMessage() {
        return context.getString(R.string.schedule_exists_error_message);
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
                return allTypes[PairTypeEnumContract.LECTURE_TYPE_INDEX];
            case PRACTICE:
                return allTypes[PairTypeEnumContract.PRACTICE_TYPE_INDEX];
            case LABORATORY:
                return allTypes[PairTypeEnumContract.LABORATORY_TYPE_INDEX];
            case SPORT:
                return allTypes[PairTypeEnumContract.SPORT_TYPE_INDEX];
            default:
                return allTypes[PairTypeEnumContract.NOT_STATED_TYPE_INDEX];
        }
    }

    public PairType getPairType(String type) {
        String[] allTypes = context.getResources().getStringArray(R.array.pairs_types);
        if (type.equals(allTypes[PairTypeEnumContract.LECTURE_TYPE_INDEX])) {
            return PairType.LECTURE;
        } else if (type.equals(allTypes[PairTypeEnumContract.PRACTICE_TYPE_INDEX])) {
            return PairType.PRACTICE;
        } else if (type.equals(allTypes[PairTypeEnumContract.LABORATORY_TYPE_INDEX])) {
            return PairType.LABORATORY;
        } else if (type.equals(allTypes[PairTypeEnumContract.SPORT_TYPE_INDEX])) {
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
                R.string.schedule_date_of_creation_form,
                formatNums(timeCalendar.get(Calendar.DAY_OF_MONTH) + ""),
                formatNums(timeCalendar.get(Calendar.MONTH) + 1 + ""),
                timeCalendar.get(Calendar.YEAR) + "",
                formatNums(timeCalendar.get(Calendar.HOUR_OF_DAY) + ""),
                formatNums(timeCalendar.get(Calendar.MINUTE) + "")
        );
    }

    private String formatNums(String number) {
        int numsCount = 2;
        if (number.length() < numsCount) {
            number = "0" + number;
        }
        return number;
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

    public String getPairTypeByIndex(int typeIndex) {
        return context.getResources().getStringArray(R.array.pairs_types)[typeIndex];
    }

    public int getPairsHolderColorByIndex(int typeIndex) {
        switch (typeIndex) {
            case PairTypeEnumContract.LECTURE_TYPE_INDEX:
                return ContextCompat.getColor(context, R.color.colorLecture);
            case PairTypeEnumContract.PRACTICE_TYPE_INDEX:
                return ContextCompat.getColor(context, R.color.colorPractice);
            case PairTypeEnumContract.LABORATORY_TYPE_INDEX:
                return ContextCompat.getColor(context, R.color.colorLaboratory);
            case PairTypeEnumContract.SPORT_TYPE_INDEX:
                return ContextCompat.getColor(context, R.color.colorSport);
            default:
                return ContextCompat.getColor(context, R.color.colorNotStated);
        }
    }

    public int getPairTypeIndex(String type) {
        String[] allTypes = context.getResources().getStringArray(R.array.pairs_types);
        if (type.equals(allTypes[PairTypeEnumContract.LECTURE_TYPE_INDEX])) {
            return PairTypeEnumContract.LECTURE_TYPE_INDEX;
        } else if (type.equals(allTypes[PairTypeEnumContract.PRACTICE_TYPE_INDEX])) {
            return PairTypeEnumContract.PRACTICE_TYPE_INDEX;
        } else if (type.equals(allTypes[PairTypeEnumContract.LABORATORY_TYPE_INDEX])) {
            return PairTypeEnumContract.LABORATORY_TYPE_INDEX;
        } else if (type.equals(allTypes[PairTypeEnumContract.SPORT_TYPE_INDEX])) {
            return PairTypeEnumContract.SPORT_TYPE_INDEX;
        } else {
            return PairTypeEnumContract.NOT_STATED_TYPE_INDEX;
        }
    }

}
