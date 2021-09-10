package com.senyk.volodymyr.schedulesapp.view.helper.resourcesprovider;

import android.content.Context;
import android.text.format.DateUtils;

import androidx.core.content.ContextCompat;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.domain.entity.PairType;

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
            return allTypes[PairType.NOT_STATED_TYPE_INDEX];
        }
        switch (type) {
            case LECTURE:
                return allTypes[PairType.LECTURE_TYPE_INDEX];
            case PRACTICE:
                return allTypes[PairType.PRACTICE_TYPE_INDEX];
            case LABORATORY:
                return allTypes[PairType.LABORATORY_TYPE_INDEX];
            case SPORT:
                return allTypes[PairType.SPORT_TYPE_INDEX];
            default:
                return allTypes[PairType.NOT_STATED_TYPE_INDEX];
        }
    }

    public PairType getPairType(String type) {
        String[] allTypes = this.context.getResources().getStringArray(R.array.pairs_types);
        if (type.equals(allTypes[PairType.LECTURE_TYPE_INDEX])) {
            return PairType.LECTURE;
        } else if (type.equals(allTypes[PairType.PRACTICE_TYPE_INDEX])) {
            return PairType.PRACTICE;
        } else if (type.equals(allTypes[PairType.LABORATORY_TYPE_INDEX])) {
            return PairType.LABORATORY;
        } else if (type.equals(allTypes[PairType.SPORT_TYPE_INDEX])) {
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
        if (type.equals(allTypes[PairType.LECTURE_TYPE_INDEX])) {
            return PairType.LECTURE_TYPE_INDEX;
        } else if (type.equals(allTypes[PairType.PRACTICE_TYPE_INDEX])) {
            return PairType.PRACTICE_TYPE_INDEX;
        } else if (type.equals(allTypes[PairType.LABORATORY_TYPE_INDEX])) {
            return PairType.LABORATORY_TYPE_INDEX;
        } else if (type.equals(allTypes[PairType.SPORT_TYPE_INDEX])) {
            return PairType.SPORT_TYPE_INDEX;
        } else {
            return PairType.NOT_STATED_TYPE_INDEX;
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
