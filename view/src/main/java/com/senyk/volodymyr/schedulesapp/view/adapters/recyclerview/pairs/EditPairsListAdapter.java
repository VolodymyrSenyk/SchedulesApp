package com.senyk.volodymyr.schedulesapp.view.adapters.recyclerview.pairs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation.NewPairCreationAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation.PairDataInputAdapterDelegate;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.PairDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.ArrayList;
import java.util.List;

public class EditPairsListAdapter extends AsyncListDifferDelegationAdapter<PrintableOnTheList> {
    private final PairDtoUiMapper pairMapper;

    public EditPairsListAdapter(Fragment fragment, PairDtoUiMapper pairMapper) {
        super(new DiffPrintableOnTheList());
        delegatesManager
                .addDelegate(new PairDataInputAdapterDelegate(fragment))
                .addDelegate(new NewPairCreationAdapterDelegate(fragment));
        setItems(new ArrayList<>());
        this.pairMapper = pairMapper;
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
        List<PrintableOnTheList> extendedList = new ArrayList<>(this.getItemCount() + 1);
        extendedList.addAll(this.getItems());
        extendedList.add(this.getItemCount() - 1, this.pairMapper.convertToUi(new PairDto(
                0,
                "",
                "",
                PairType.NOT_STATED,
                "",
                ""
        )));
        this.setItems(extendedList);
    }

    public void deletePair(PairUi pair) {
        List<PrintableOnTheList> croppedList = new ArrayList<>();
        boolean isPairDeleted = false;
        for (PrintableOnTheList item : this.getItems()) {
            if (item instanceof PairUi) {
                if (isPairDeleted || ((PairUi) item).getId() != pair.getId()) {
                    croppedList.add(item);
                } else {
                    isPairDeleted = true;
                }
            } else {
                croppedList.add(item);
            }
        }
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
            return ((PairUi) oldItem).getId() == ((PairUi) newItem).getId();
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
