package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.schedules.output;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.listeners.SchedulesClickListener;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import java.util.List;

public class ScheduleDataOutputAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private final LayoutInflater inflater;
    private final SchedulesClickListener clickListener;

    public ScheduleDataOutputAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
        this.clickListener = (SchedulesClickListener) fragment;
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return items.get(position) instanceof ScheduleUi;
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
            @NonNull List<PrintableOnTheList> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        ScheduleUi item = (ScheduleUi) items.get(position);
        ScheduleDataOutputViewHolder viewHolder = (ScheduleDataOutputViewHolder) holder;

        viewHolder.scheduleName.setText(item.getName());
        viewHolder.scheduleDateOfCreation.setText(
                viewHolder.scheduleDateOfCreation.getContext().getString(
                        R.string.schedule_date_of_creation_output,
                        item.getDateOfCreation()
                )
        );
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

        viewHolder.itemView.setBackgroundColor(item.getScheduleHolderColor());

        viewHolder.itemView.setOnClickListener(view ->
                clickListener.scheduleClicked(viewHolder.scheduleName.getText().toString()));
        viewHolder.deleteScheduleButton.setOnClickListener(view ->
                clickListener.scheduleDeleteClicked(viewHolder.scheduleName.getText().toString()));
    }

    static class ScheduleDataOutputViewHolder extends RecyclerView.ViewHolder {
        TextView scheduleName;
        TextView scheduleDateOfCreation;
        TextView scheduleIsSaturdayWorking;
        TextView scheduleIsNumDenomSystem;
        MaterialButton deleteScheduleButton;

        ScheduleDataOutputViewHolder(View view) {
            super(view);
            scheduleName = view.findViewById(R.id.schedule_name_field);
            scheduleDateOfCreation = view.findViewById(R.id.schedule_date_of_creation);
            scheduleIsSaturdayWorking = view.findViewById(R.id.schedule_is_sat_working);
            scheduleIsNumDenomSystem = view.findViewById(R.id.schedule_is_num_denom_system);
            deleteScheduleButton = view.findViewById(R.id.delete_schedule_bottom_button);
        }
    }

}
