package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules;

import android.app.Activity;

import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.output.ScheduleDataOutputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base.BaseRecyclerViewAdapter;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.ScheduleOutput;

import java.util.List;

public class SchedulesOutputAdapter extends BaseRecyclerViewAdapter<ScheduleOutput> {

    public SchedulesOutputAdapter(Activity activity, List<ScheduleOutput> items) {
        delegatesManager
                .addDelegate(new ScheduleDataOutputAdapterDelegate(activity));
        setItems(items);
    }

}
