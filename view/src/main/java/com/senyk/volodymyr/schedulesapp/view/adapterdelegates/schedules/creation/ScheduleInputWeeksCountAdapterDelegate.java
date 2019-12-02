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
import com.senyk.volodymyr.schedulesapp.viewmodel.models.datainputfields.ScheduleIsNumDenomSystemCheckField;

import java.util.List;

public class ScheduleInputWeeksCountAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private LayoutInflater inflater;

    public ScheduleInputWeeksCountAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
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
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<PrintableOnTheList> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        ScheduleWeeksCountViewHolder viewHolder = (ScheduleWeeksCountViewHolder) holder;
        viewHolder.isNumDenomSystemView.setOnClickListener(view -> {
            if (viewHolder.isNumDenomSystemView.isChecked()) {
                viewHolder.isNumDenomSystemView.setChecked(false);
            } else {
                viewHolder.isNumDenomSystemView.setChecked(true);
            }
            ((ScheduleIsNumDenomSystemCheckField)items.get(position)).setChecked(viewHolder.isNumDenomSystemView.isChecked());
        });
    }

    static class ScheduleWeeksCountViewHolder extends RecyclerView.ViewHolder {
        private AppCompatCheckedTextView isNumDenomSystemView;

        ScheduleWeeksCountViewHolder(View view) {
            super(view);
            this.isNumDenomSystemView = view.findViewById(R.id.new_schedule_is_num_denom_system);
        }
    }
}
