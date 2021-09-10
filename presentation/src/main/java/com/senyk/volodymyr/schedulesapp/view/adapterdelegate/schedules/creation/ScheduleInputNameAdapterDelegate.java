package com.senyk.volodymyr.schedulesapp.view.adapterdelegate.schedules.creation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegate.helper.TextChangeListener;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegate.listener.NewScheduleNameFieldListener;
import com.senyk.volodymyr.schedulesapp.view.entity.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.view.entity.datainputfields.ScheduleNameInputField;

import java.util.List;

public class ScheduleInputNameAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private final LayoutInflater inflater;
    private final NewScheduleNameFieldListener fieldFillListener;

    public ScheduleInputNameAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
        this.fieldFillListener = (NewScheduleNameFieldListener) fragment;
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return items.get(position) instanceof ScheduleNameInputField;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ScheduleInputNameAdapterDelegate.ScheduleNameInputFieldViewHolder(this.inflater.inflate(
                R.layout.input_field_schedule_name,
                parent,
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<PrintableOnTheList> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        ScheduleNameInputFieldViewHolder viewHolder = (ScheduleNameInputFieldViewHolder) holder;
        viewHolder.scheduleNameView.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    fieldFillListener.scheduleNameFieldFilled();
                } else {
                    fieldFillListener.scheduleNameFieldCleared();
                }
                ((ScheduleNameInputField) items.get(position)).setName(editable.toString().trim());
            }
        });
    }

    static class ScheduleNameInputFieldViewHolder extends RecyclerView.ViewHolder {
        private TextInputEditText scheduleNameView;

        ScheduleNameInputFieldViewHolder(View view) {
            super(view);
            this.scheduleNameView = view.findViewById(R.id.new_schedule_name);
        }
    }
}
