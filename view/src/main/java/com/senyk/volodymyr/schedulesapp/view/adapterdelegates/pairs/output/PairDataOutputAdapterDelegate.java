package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.output;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.List;

public class PairDataOutputAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private final LayoutInflater inflater;

    public PairDataOutputAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return items.get(position) instanceof PairUi;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new PairDataOutputAdapterDelegate.PairDataOutputViewHolder(this.inflater.inflate(
                R.layout.list_item_pair_output,
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
        PairDataOutputViewHolder viewHolder = (PairDataOutputViewHolder) holder;

        viewHolder.itemView.setBackgroundColor(item.getHolderColor());

        viewHolder.pairTime.setText(item.getTime());
        viewHolder.pairName.setText(item.getName());
        viewHolder.pairTeacher.setText(item.getTeacher());
        viewHolder.pairType.setText(item.getType());
        viewHolder.pairPlace.setText(item.getPlace());
    }

    static class PairDataOutputViewHolder extends RecyclerView.ViewHolder {
        TextView pairTime;
        TextView pairName;
        TextView pairTeacher;
        TextView pairType;
        TextView pairPlace;

        PairDataOutputViewHolder(View view) {
            super(view);
            pairTime = view.findViewById(R.id.pair_time_output);
            pairName = view.findViewById(R.id.pair_name_output);
            pairTeacher = view.findViewById(R.id.pair_teacher_output);
            pairType = view.findViewById(R.id.pair_type_output);
            pairPlace = view.findViewById(R.id.pair_place_output);
        }
    }

}
