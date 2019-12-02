package com.senyk.volodymyr.schedulesapp.viewmodel.helpers;

import android.content.Context;
import android.text.format.DateUtils;

import androidx.core.content.ContextCompat;

import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;
import com.senyk.volodymyr.schedulesapp.model.models.enums.PairTypeEnumContract;
import com.senyk.volodymyr.schedulesapp.viewmodel.R;

import java.util.Calendar;

public class ResourcesProvider {
    private final Context context;

    public ResourcesProvider(Context context) {
        this.context = context;
    }

    public String getErrorMessage(String errorMessage) {
        return context.getString(R.string.error_message, errorMessage);
    }

    public String getPairTime(long time) {
        if (time == 0) {
            return "";
        }
        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(time);
        return DateUtils.formatDateTime(
                context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME
        );
    }

    public String getPairType(PairType type) {
        String[] allTypes = context.getResources().getStringArray(R.array.pairs_types);
        if (type == null) {
            return allTypes[PairTypeEnumContract.NOT_STATED_TYPE_INDEX];
        }
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
        if (type == null) {
            return ContextCompat.getColor(context, R.color.colorNotStated);
        }
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

    public String getDateTime(long date) {
        if (date == 0) {
            return "";
        }
        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(date);
        return DateUtils.formatDateTime(
                context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME
        );
    }

    public String getPairTypeByIndex(int typeIndex) {
        return context.getResources().getStringArray(R.array.pairs_types)[typeIndex];
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
