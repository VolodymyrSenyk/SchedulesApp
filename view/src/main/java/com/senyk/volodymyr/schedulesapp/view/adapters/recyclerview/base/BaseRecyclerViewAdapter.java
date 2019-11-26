package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base;

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends ListDelegationAdapter<List<T>> {
    @Override
    public void setItems(List<T> items) {
        super.setItems(items);
        this.notifyDataSetChanged();
    }
}
