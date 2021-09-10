package com.senyk.volodymyr.schedulesapp.view.adapter.recyclerview.pairs;

import androidx.fragment.app.Fragment;

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegate.pairs.output.PairDataOutputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegate.pairs.output.PairOutputEmptyStateAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.entity.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.view.entity.PairUi;

import java.util.ArrayList;
import java.util.List;

public class ShowPairsListAdapter extends ListDelegationAdapter<List<PrintableOnTheList>> {
    public ShowPairsListAdapter(Fragment fragment) {
        this.delegatesManager
                .addDelegate(new PairDataOutputAdapterDelegate(fragment))
                .addDelegate(new PairOutputEmptyStateAdapterDelegate(fragment));
        setItems(new ArrayList<>());
    }

    public void setUiItems(List<PairUi> items) {
        List<PrintableOnTheList> convertedList = new ArrayList<>(items.size());
        convertedList.addAll(items);
        if (convertedList.isEmpty()) {
            convertedList.add(new PrintableOnTheList() {});
        }
        super.setItems(convertedList);
    }
}
