package com.senyk.volodymyr.schedulesapp.view.adapterdelegate.pairs.output;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.entity.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.view.entity.PairUi;

import java.util.List;

public class PairOutputEmptyStateAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private final LayoutInflater inflater;

    public PairOutputEmptyStateAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return !(items.get(position) instanceof PairUi);
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new PairOutputEmptyStateAdapterDelegate.PairDataOutputEmptyStateViewHolder(this.inflater.inflate(
                R.layout.list_item_pair_output_empty_state,
                parent,
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<PrintableOnTheList> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
    }

    static class PairDataOutputEmptyStateViewHolder extends RecyclerView.ViewHolder {
        PairDataOutputEmptyStateViewHolder(View view) {
            super(view);
        }
    }
}
