package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation.NewPairCreationAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation.PairDataInputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.ArrayList;
import java.util.List;

public class EditPairsListAdapter extends AsyncListDifferDelegationAdapter<PrintableOnTheList> {
    public EditPairsListAdapter(Fragment fragment) {
        super(new DiffPrintableOnTheList());
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
                    !pair.getTeacher().equals("") || !pair.getPlace().equals("")) {
                filteredList.add(pair);
            }
        }
        return filteredList;
    }

    public void addNewPair() {
        List<PrintableOnTheList> extendedList = new ArrayList<>();
        for (PrintableOnTheList item : this.getItems()) {
            if (item instanceof PairUi) {
                extendedList.add(item);
            }
        }
        extendedList.add(new PairUi());
        extendedList.add(new PrintableOnTheList() {
        });
        this.setItems(extendedList);
    }

    public void deletePair(PairUi pair) {
        List<PrintableOnTheList> croppedList = new ArrayList<>();
        boolean isPairDeleted = false;
        for (PrintableOnTheList item : this.getItems()) {
            if (item instanceof PairUi) {
                if (!item.equals(pair) || isPairDeleted) {
                    croppedList.add(item);
                } else {
                    isPairDeleted = true;
                }
            }
        }
        croppedList.add(new PrintableOnTheList() {
        });
        this.setItems(croppedList);
    }

    static class DiffPrintableOnTheList extends DiffUtil.ItemCallback<PrintableOnTheList> {
        @Override
        public boolean areItemsTheSame(@NonNull PrintableOnTheList oldItem, @NonNull PrintableOnTheList newItem) {
            if (oldItem.getClass() != newItem.getClass()) {
                return false;
            }
            if (oldItem.getClass() != PairUi.class && newItem.getClass() != PairUi.class) {
                return true;
            }
            return oldItem.hashCode() == newItem.hashCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PrintableOnTheList oldItem, @NonNull PrintableOnTheList newItem) {
            if (!(oldItem instanceof PairUi && newItem instanceof PairUi)) {
                return false;
            }
            PairUi oldPair = (PairUi) oldItem;
            PairUi newPair = (PairUi) newItem;
            return oldPair.equals(newPair);
        }
    }
}
