package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.helpers;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public abstract class DateTimeSetter {
    protected final TextInputEditText textView;
    protected final Calendar dateAndTime;

    public long getDateAndTime() {
        return this.dateAndTime.getTimeInMillis();
    }

    public void setDateAndTime(long dateAndTime) {
        this.dateAndTime.setTimeInMillis(dateAndTime);
    }

    protected DateTimeSetter(TextInputEditText view) {
        this.textView = view;
        this.dateAndTime = Calendar.getInstance();
    }

    protected abstract void setDialog();

}
