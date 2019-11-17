package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {
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
