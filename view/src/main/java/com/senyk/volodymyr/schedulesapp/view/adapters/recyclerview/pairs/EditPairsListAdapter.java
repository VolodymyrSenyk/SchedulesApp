package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs;

import androidx.fragment.app.Fragment;

import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation.NewPairCreationAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation.PairDataInputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.base.BaseRecyclerViewAdapter;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.ArrayList;
import java.util.List;

public class EditPairsListAdapter extends BaseRecyclerViewAdapter<PrintableOnTheList> {
    public EditPairsListAdapter(Fragment fragment, List<PrintableOnTheList> items) {
        delegatesManager
                .addDelegate(new PairDataInputAdapterDelegate(fragment))
                .addDelegate(new NewPairCreationAdapterDelegate(fragment));
        setItems(items);
    }

    public void setUiItems(List<PairUi> items) {
        List<PrintableOnTheList> convertedList = new ArrayList<>(items.size());
        convertedList.addAll(items);
        convertedList.add(new PrintableOnTheList() {
        });
        super.setItems(convertedList);
    }
}
