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
    public EditPairsListAdapter(Fragment fragment) {
        delegatesManager
                .addDelegate(new PairDataInputAdapterDelegate(fragment))
                .addDelegate(new NewPairCreationAdapterDelegate(fragment));
        setItems(new ArrayList<>());
    }

    public void setPairsList(List<PairUi> items) {
        List<PrintableOnTheList> convertedList = new ArrayList<>(items.size());
        convertedList.addAll(items);
        convertedList.add(new PrintableOnTheList() {
        });
        super.setItems(convertedList);
    }

    public List<PairUi> getPairsList() {
        List<PairUi> convertedList = new ArrayList<>(getItemCount());
        for (PrintableOnTheList item : this.getItems()) {
            if (item.getClass() == PairUi.class) {
                convertedList.add((PairUi) item);
            }
        }
        List<PairUi> filteredList = new ArrayList<>(convertedList.size());
        for (PairUi pair : convertedList) {
            if (!pair.getTime().equals("") || !pair.getName().equals("") ||
                    !pair.getTeacher().equals("") || !pair.getPlace().equals("") ||
                    !pair.getType().equals("") || !pair.getAdditionalInfo().equals("")) {
                filteredList.add(pair);
            }
        }
        return filteredList;
    }

    public void addNewPair() {
        this.getItems().set(this.getItemCount() - 1, new PairUi());
        this.getItems().add(new PrintableOnTheList() {
        });
        notifyDataSetChanged();
    }

    public void deletePair(PairUi pair) {
        this.getItems().remove(pair);
        notifyDataSetChanged();
    }
}
