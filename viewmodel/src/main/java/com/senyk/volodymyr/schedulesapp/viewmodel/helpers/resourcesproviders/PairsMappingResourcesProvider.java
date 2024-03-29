package com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders;

import android.content.Context;
import android.text.format.DateUtils;

import androidx.core.content.ContextCompat;

import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;
import com.senyk.volodymyr.schedulesapp.model.models.enums.PairTypeEnumContract;
import com.senyk.volodymyr.schedulesapp.viewmodel.R;

import java.util.Calendar;

public class PairsMappingResourcesProvider {
    private final Context context;

    public PairsMappingResourcesProvider(Context context) {
        this.context = context;
    }

    public String getPairTime(long time) {
        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(time);
        return DateUtils.formatDateTime(
                this.context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME
        );
    }

    public String getPairType(PairType type) {
        String[] allTypes = this.context.getResources().getStringArray(R.array.pairs_types);
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
        String[] allTypes = this.context.getResources().getStringArray(R.array.pairs_types);
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

    public String getPairTypeByIndex(int typeIndex) {
        return this.context.getResources().getStringArray(R.array.pairs_types)[typeIndex];
    }

    public int getPairTypeIndex(String type) {
        String[] allTypes = this.context.getResources().getStringArray(R.array.pairs_types);
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

    public int getPairsHolderColor(PairType type) {
        if (type == null) {
            return ContextCompat.getColor(this.context, R.color.colorNotStated);
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
}
