package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.creation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields.ScheduleIsSaturdayWorkingCheckField;

import java.util.List;

public class ScheduleInputWeekLengthAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private LayoutInflater inflater;

    public ScheduleInputWeekLengthAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
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
            @NonNull List<PrintableOnTheList> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        ScheduleInputWeekLengthViewHolder viewHolder = (ScheduleInputWeekLengthViewHolder) holder;
        viewHolder.isSatWorkingView.setOnClickListener(view -> {
            if (viewHolder.isSatWorkingView.isChecked()) {
                viewHolder.isSatWorkingView.setChecked(false);
            } else {
                viewHolder.isSatWorkingView.setChecked(true);
            }
            ((ScheduleIsSaturdayWorkingCheckField) items.get(position)).setChecked(viewHolder.isSatWorkingView.isChecked());
        });
    }

    static class ScheduleInputWeekLengthViewHolder extends RecyclerView.ViewHolder {
        private AppCompatCheckedTextView isSatWorkingView;

        ScheduleInputWeekLengthViewHolder(View view) {
            super(view);
            this.isSatWorkingView = view.findViewById(R.id.new_schedule_is_sat_working);
        }
    }

}
