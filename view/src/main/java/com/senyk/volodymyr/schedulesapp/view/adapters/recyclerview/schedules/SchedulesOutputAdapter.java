package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.schedules;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.output.ScheduleDataOutputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import java.util.ArrayList;
import java.util.List;

public class SchedulesOutputAdapter extends AsyncListDifferDelegationAdapter<PrintableOnTheList> {
    public SchedulesOutputAdapter(Fragment fragment, List<PrintableOnTheList> items) {
        super(new DiffPrintableOnTheList());
        delegatesManager
                .addDelegate(new ScheduleDataOutputAdapterDelegate(fragment));
        setItems(items);
    }

    public void setUiItems(List<ScheduleUi> list) {
        List<PrintableOnTheList> convertedList = new ArrayList<>(list.size());
        convertedList.addAll(list);
        super.setItems(convertedList);
    }

    static class DiffPrintableOnTheList extends DiffUtil.ItemCallback<PrintableOnTheList> {
        @Override
        public boolean areItemsTheSame(@NonNull PrintableOnTheList oldItem, @NonNull PrintableOnTheList newItem) {
            if (oldItem.getClass() != newItem.getClass()) {
                return false;
            }
            if (!(oldItem instanceof ScheduleUi && newItem instanceof ScheduleUi)) {
                return false;
            }
            return ((ScheduleUi) oldItem).getName().equals(((ScheduleUi) newItem).getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PrintableOnTheList oldItem, @NonNull PrintableOnTheList newItem) {
            if (!(oldItem instanceof ScheduleUi && newItem instanceof ScheduleUi)) {
                return false;
            }
            ScheduleUi oldSchedule = (ScheduleUi) oldItem;
            ScheduleUi newSchedule = (ScheduleUi) newItem;
            return oldSchedule.equals(newSchedule);
        }
    }
}
