package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs;

import android.app.Activity;

import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.PairDataOutputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base.BaseRecyclerViewAdapter;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

import java.util.List;

public class ShowPairsListAdapter extends BaseRecyclerViewAdapter<PrintableOnTheList> {

    public ShowPairsListAdapter(Activity activity, List<PrintableOnTheList> items) {
        delegatesManager
                .addDelegate(new PairDataOutputAdapterDelegate(activity));
        setItems(items);
    }

}
