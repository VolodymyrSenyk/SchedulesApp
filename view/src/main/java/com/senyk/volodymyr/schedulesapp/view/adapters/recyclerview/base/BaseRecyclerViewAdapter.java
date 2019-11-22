package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base;

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends ListDelegationAdapter<List<T>> {
    private List<T> itemsList;

    public void setItemsList(List<T> itemsList) {
        this.itemsList = itemsList;
        this.notifyDataSetChanged();
    }

    public List<T> getItemsList() {
        return this.itemsList;
    }

    @Override
    public int getItemCount() {
        return this.itemsList.size();
    }
}
