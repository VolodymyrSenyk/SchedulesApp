package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.output;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.dialogs.builder.AlertDialogBuilder;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.List;

public class PairDataOutputAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private static final float CORNER_RADIUS = 30;
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
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<PrintableOnTheList> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        PairUi item = (PairUi) items.get(position);
        PairDataOutputViewHolder viewHolder = (PairDataOutputViewHolder) holder;

        viewHolder.pairTime.setText(item.getTime());
        viewHolder.pairName.setText(item.getName());
        viewHolder.pairTeacher.setText(item.getTeacher());
        viewHolder.pairType.setText(item.getType());
        viewHolder.pairPlace.setText(item.getPlace());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(item.getHolderColor());
            if (items.size() == 1) {
                drawable.setCornerRadii(new float[]{CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS});
            } else if (items.get(0).equals(item)) {
                drawable.setCornerRadii(new float[]{CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, 0, 0, 0, 0});
            } else if (items.get(items.size() - 1).equals(item)) {
                drawable.setCornerRadii(new float[]{0, 0, 0, 0, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS});
            }
            viewHolder.itemView.setBackground(drawable);
        } else {
            viewHolder.itemView.setBackgroundColor(item.getHolderColor());
        }

        viewHolder.itemView.setOnClickListener(view -> {
            if (!item.getAdditionalInfo().equals("")) {
                new AlertDialogBuilder(view.getContext()).addMessage(item.getAdditionalInfo()).build().show();
            }
        });
    }

    static class PairDataOutputViewHolder extends RecyclerView.ViewHolder {
        private TextView pairTime;
        private TextView pairName;
        private TextView pairTeacher;
        private TextView pairType;
        private TextView pairPlace;

        PairDataOutputViewHolder(View view) {
            super(view);
            this.pairTime = view.findViewById(R.id.pair_time_output);
            this.pairName = view.findViewById(R.id.pair_name_output);
            this.pairTeacher = view.findViewById(R.id.pair_teacher_output);
            this.pairType = view.findViewById(R.id.pair_type_output);
            this.pairPlace = view.findViewById(R.id.pair_place_output);
        }
    }
}
