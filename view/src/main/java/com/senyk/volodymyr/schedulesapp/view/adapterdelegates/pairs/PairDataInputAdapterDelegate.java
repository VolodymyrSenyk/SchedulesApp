package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.List;

public class PairDataInputAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private LayoutInflater inflater;

    public PairDataInputAdapterDelegate(Activity activity) {
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return items.get(position) instanceof PairUi;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new PairDataInputAdapterDelegate.PairDataInputViewHolder(this.inflater.inflate(
                R.layout.list_item_pair_input,
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
        PairUi item = (PairUi) items.get(position);
        PairDataInputViewHolder viewHolder = (PairDataInputViewHolder) holder;

        viewHolder.pairTime.setText(item.getTime());
        viewHolder.pairName.setText(item.getName());
        viewHolder.pairTeacher.setText(item.getTeacher());
     //   viewHolder.pairType.setSelection(item.getType());
        viewHolder.pairPlace.setText(item.getPlace());
        viewHolder.pairAdditionalInfo.setText(item.getAdditionalInfo());
    }

    static class PairDataInputViewHolder extends RecyclerView.ViewHolder {
        TextView pairTime;
        TextView pairName;
        TextView pairTeacher;
        Spinner pairType;
        TextView pairPlace;
        TextView pairAdditionalInfo;

        PairDataInputViewHolder(View view) {
            super(view);
            pairTime = view.findViewById(R.id.pair_time_input);
            pairName = view.findViewById(R.id.pair_name_input);
            pairTeacher = view.findViewById(R.id.pair_teacher_input);
            pairType = view.findViewById(R.id.pair_type_output);
            pairPlace = view.findViewById(R.id.pair_place_input);
            pairAdditionalInfo = view.findViewById(R.id.pair_additional_info_input);
        }
    }

}
