package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.ScheduleCreationField;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.schedules.creation.ScheduleIsSaturdayWorkingCheckField;

import java.util.List;

public class ScheduleInputWeekLengthAdapterDelegate extends AdapterDelegate<List<ScheduleCreationField>> {
    private LayoutInflater inflater;

    public ScheduleInputWeekLengthAdapterDelegate(Activity activity) {
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<ScheduleCreationField> items, int position) {
        return items.get(position) instanceof ScheduleIsSaturdayWorkingCheckField;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ScheduleInputWeekLengthAdapterDelegate.ScheduleInputWeekLengthViewHolder(this.inflater.inflate(
                R.layout.input_field_schedule_is_saturday_working,
                parent,
                false
        ));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<ScheduleCreationField> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        ((CheckedTextView) holder.itemView)
                .setChecked(((ScheduleIsSaturdayWorkingCheckField) items.get(position)).isChecked());
    }

    static class ScheduleInputWeekLengthViewHolder extends RecyclerView.ViewHolder {
        ScheduleInputWeekLengthViewHolder(View view) {
            super(view);
        }
    }

}
