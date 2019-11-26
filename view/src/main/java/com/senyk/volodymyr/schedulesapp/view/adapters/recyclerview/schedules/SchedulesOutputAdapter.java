package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules;

import androidx.fragment.app.Fragment;

import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.output.ScheduleDataOutputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base.BaseRecyclerViewAdapter;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import java.util.ArrayList;
import java.util.List;

public class SchedulesOutputAdapter extends BaseRecyclerViewAdapter<PrintableOnTheList> {
    public SchedulesOutputAdapter(Fragment fragment, List<PrintableOnTheList> items) {
        delegatesManager
                .addDelegate(new ScheduleDataOutputAdapterDelegate(fragment));
        setItems(items);
    }

    public void setUiItems(List<ScheduleUi> list) {
        List<PrintableOnTheList> convertedList = new ArrayList<>(list.size());
        convertedList.addAll(list);
        super.setItems(convertedList);
    }
}
