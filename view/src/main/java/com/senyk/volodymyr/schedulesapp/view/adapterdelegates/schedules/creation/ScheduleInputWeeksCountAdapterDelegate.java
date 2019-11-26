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
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields.ScheduleIsNumDenomSystemCheckField;

import java.util.List;

public class ScheduleInputWeeksCountAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private LayoutInflater inflater;

    public ScheduleInputWeeksCountAdapterDelegate(Activity activity) {
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return items.get(position) instanceof ScheduleIsNumDenomSystemCheckField;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ScheduleInputWeeksCountAdapterDelegate.ScheduleWeeksCountViewHolder(this.inflater.inflate(
                R.layout.input_field_schedule_is_num_denom_system,
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
        ((CheckedTextView) holder.itemView)
                .setChecked(((ScheduleIsNumDenomSystemCheckField) items.get(position)).isChecked());
    }

    static class ScheduleWeeksCountViewHolder extends RecyclerView.ViewHolder {
        ScheduleWeeksCountViewHolder(View view) {
            super(view);
        }
    }

}
