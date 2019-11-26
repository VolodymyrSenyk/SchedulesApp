package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules;

import android.app.Activity;

import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.output.ScheduleDataOutputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base.BaseRecyclerViewAdapter;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

import java.util.List;

public class SchedulesOutputAdapter extends BaseRecyclerViewAdapter<PrintableOnTheList> {

    public SchedulesOutputAdapter(Activity activity, List<PrintableOnTheList> items) {
        delegatesManager
                .addDelegate(new ScheduleDataOutputAdapterDelegate(activity));
        setItems(items);
    }

}
