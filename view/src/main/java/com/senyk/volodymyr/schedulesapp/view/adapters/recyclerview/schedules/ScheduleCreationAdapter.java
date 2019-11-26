package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules;

import android.app.Activity;

import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation.ScheduleInputNameAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation.ScheduleInputWeekLengthAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation.ScheduleInputWeeksCountAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base.BaseRecyclerViewAdapter;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

import java.util.List;

public class ScheduleCreationAdapter extends BaseRecyclerViewAdapter<PrintableOnTheList> {

    public ScheduleCreationAdapter(Activity activity, List<PrintableOnTheList> items) {
        delegatesManager
                .addDelegate(new ScheduleInputNameAdapterDelegate(activity))
                .addDelegate(new ScheduleInputWeekLengthAdapterDelegate(activity))
                .addDelegate(new ScheduleInputWeeksCountAdapterDelegate(activity));
        setItems(items);
    }

}
