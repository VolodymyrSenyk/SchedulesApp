package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules;

import androidx.fragment.app.Fragment;

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation.ScheduleInputNameAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation.ScheduleInputWeekLengthAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation.ScheduleInputWeeksCountAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

import java.util.List;

public class ScheduleCreationAdapter extends ListDelegationAdapter<List<PrintableOnTheList>> {
    public ScheduleCreationAdapter(Fragment fragment, List<PrintableOnTheList> items) {
        delegatesManager
                .addDelegate(new ScheduleInputNameAdapterDelegate(fragment))
                .addDelegate(new ScheduleInputWeekLengthAdapterDelegate(fragment))
                .addDelegate(new ScheduleInputWeeksCountAdapterDelegate(fragment));
        setItems(items);
    }
}
