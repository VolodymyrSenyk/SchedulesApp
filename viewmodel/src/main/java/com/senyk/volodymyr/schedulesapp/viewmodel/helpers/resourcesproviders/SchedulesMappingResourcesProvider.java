package com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders;

import android.content.Context;
import android.text.format.DateUtils;

import java.util.Calendar;

public class SchedulesMappingResourcesProvider {
    private final Context context;

    public SchedulesMappingResourcesProvider(Context context) {
        this.context = context;
    }

    public String getDateOfCreation(long date) {
        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(date);
        return DateUtils.formatDateTime(
                this.context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME);
    }
}
