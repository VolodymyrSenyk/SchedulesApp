package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs;

import androidx.fragment.app.Fragment;

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.output.PairDataOutputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.output.PairOutputEmptyStateAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

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
