package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.listeners.PairsClickListener;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.List;

public class NewPairCreationAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private final LayoutInflater inflater;
    private final PairsClickListener listener;

    public NewPairCreationAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
        this.listener = (PairsClickListener) fragment;
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return !(items.get(position) instanceof PairUi);
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new NewPairCreationAdapterDelegate.PairCreationViewHolder(this.inflater.inflate(
                R.layout.list_item_add_pair,
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
        PairCreationViewHolder viewHolder = (PairCreationViewHolder) holder;
        viewHolder.button.setOnClickListener(view -> listener.pairAddButtonClicked());
    }

    static class PairCreationViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageButton button;

        PairCreationViewHolder(View view) {
            super(view);
            this.button = view.findViewById(R.id.add_pair_button);
        }
    }

}
