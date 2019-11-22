package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.output;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.ScheduleOutput;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.schedules.output.ScheduleOutputItem;

import java.util.List;

public class ScheduleDataOutputAdapterDelegate extends AdapterDelegate<List<ScheduleOutput>> {
    private LayoutInflater inflater;

    public ScheduleDataOutputAdapterDelegate(Activity activity) {
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<ScheduleOutput> items, int position) {
        return items.get(position) instanceof ScheduleOutputItem;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ScheduleDataOutputAdapterDelegate.ScheduleDataOutputViewHolder(this.inflater.inflate(
                R.layout.list_item_schedule_data,
                parent,
                false
        ));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<ScheduleOutput> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        ScheduleOutputItem item = (ScheduleOutputItem) items.get(position);
        ScheduleDataOutputViewHolder viewHolder = (ScheduleDataOutputViewHolder) holder;

        viewHolder.scheduleName.setText(item.getName());
        viewHolder.scheduleName.setText(item.getDateOfCreation());
        if (item.isSaturdayWorking()) {
            viewHolder.scheduleIsSaturdayWorking.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true, 0);
        } else {
            viewHolder.scheduleIsSaturdayWorking.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_false, 0);
        }
        if (item.isNumDenomSystem()) {
            viewHolder.scheduleIsNumDenomSystem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true, 0);
        } else {
            viewHolder.scheduleIsNumDenomSystem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_false, 0);
        }
    }

    static class ScheduleDataOutputViewHolder extends RecyclerView.ViewHolder {
        TextView scheduleName;
        TextView scheduleDateOfCreation;
        TextView scheduleIsSaturdayWorking;
        TextView scheduleIsNumDenomSystem;

        ScheduleDataOutputViewHolder(View view) {
            super(view);
            scheduleName = view.findViewById(R.id.schedule_name_field);
            scheduleDateOfCreation = view.findViewById(R.id.schedule_date_of_creation);
            scheduleIsSaturdayWorking = view.findViewById(R.id.schedule_is_sat_working);
            scheduleIsNumDenomSystem = view.findViewById(R.id.schedule_is_num_denom_system);
        }
    }

}
