package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs;

import android.app.Activity;

import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.PairDataInputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base.BaseRecyclerViewAdapter;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;

import java.util.List;

public class EditPairsListAdapter extends BaseRecyclerViewAdapter<PrintableOnTheList> {

    public EditPairsListAdapter(Activity activity, List<PrintableOnTheList> items) {
        delegatesManager
                .addDelegate(new PairDataInputAdapterDelegate(activity));
        setItems(items);
    }

}
