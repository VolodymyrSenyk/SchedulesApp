package com.senyk.volodymyr.schedulesapp.view.adapterdelegates.pairs.creation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.helpers.TextChangeListener;
import com.senyk.volodymyr.schedulesapp.view.adapterdelegates.helpers.TimeSetter;
import com.senyk.volodymyr.schedulesapp.view.listeners.PairsClickListener;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.PrintableOnTheList;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import java.util.List;

public class PairDataInputAdapterDelegate extends AdapterDelegate<List<PrintableOnTheList>> {
    private final LayoutInflater inflater;
    private final ResourcesProvider resourcesProvider;
    private final PairsClickListener listener;

    public PairDataInputAdapterDelegate(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
        this.resourcesProvider = new ResourcesProvider(fragment.getContext());
        this.listener = (PairsClickListener) fragment;
    }

    @Override
    protected boolean isForViewType(@NonNull List<PrintableOnTheList> items, int position) {
        return items.get(position) instanceof PairUi;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new PairDataInputAdapterDelegate.PairDataInputViewHolder(this.inflater.inflate(
                R.layout.list_item_pair_input,
                parent,
                false
        ));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull List<PrintableOnTheList> items,
            int position,
            @NonNull RecyclerView.ViewHolder holder,
            @NonNull List<Object> payloads) {
        PairUi item = (PairUi) items.get(position);
        PairDataInputViewHolder viewHolder = (PairDataInputViewHolder) holder;
        if (!item.equals(new PairUi())) {
            setData(viewHolder, item);
        }
        setListeners(viewHolder, item);
    }

    private void setData(PairDataInputViewHolder viewHolder, PairUi item) {
        viewHolder.pairTime.setText(item.getTime());
        viewHolder.pairName.setText(item.getName());
        viewHolder.pairTeacher.setText(item.getTeacher());
        viewHolder.pairType.setSelection(resourcesProvider.getPairTypeIndex(item.getType()));
        viewHolder.pairPlace.setText(item.getPlace());
        viewHolder.pairAdditionalInfo.setText(item.getAdditionalInfo());
    }

    private void setListeners(PairDataInputViewHolder viewHolder, PairUi item) {
        viewHolder.pairTime.setFocusableInTouchMode(false);
        viewHolder.pairTime.setFocusable(false);
        TimeSetter timeSetter = new TimeSetter(viewHolder.pairTime);
        timeSetter.setDateAndTime(item.getTimeInMillis());
        viewHolder.pairTime.setOnClickListener(view -> timeSetter.setDialog());
        viewHolder.pairTime.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals("")) {
                    item.setTime(editable.toString());
                    item.setTimeInMillis(timeSetter.getDateAndTime());
                }
            }
        });
        viewHolder.pairName.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                item.setName(editable.toString().trim());
            }
        });
        viewHolder.pairTeacher.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                item.setTeacher(editable.toString().trim());
            }
        });
        viewHolder.pairType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedItemView, int position, long id) {
                item.setType(resourcesProvider.getPairTypeByIndex(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        viewHolder.pairPlace.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                item.setPlace(editable.toString().trim());
            }
        });
        viewHolder.pairAdditionalInfo.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                item.setAdditionalInfo(editable.toString().trim());
            }
        });
        viewHolder.deletePairButton.setOnClickListener(view -> listener.pairDeleteButtonClicked(item));
    }

    static class PairDataInputViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText pairTime;
        TextInputEditText pairName;
        TextInputEditText pairTeacher;
        Spinner pairType;
        TextInputEditText pairPlace;
        TextInputEditText pairAdditionalInfo;
        AppCompatImageButton deletePairButton;

        PairDataInputViewHolder(View view) {
            super(view);
            pairTime = view.findViewById(R.id.pair_time_input);
            pairName = view.findViewById(R.id.pair_name_input);
            pairTeacher = view.findViewById(R.id.pair_teacher_input);
            pairType = view.findViewById(R.id.pair_type_input_spinner);
            pairPlace = view.findViewById(R.id.pair_place_input);
            pairAdditionalInfo = view.findViewById(R.id.pair_additional_info_input);
            deletePairButton = view.findViewById(R.id.delete_pair_button);
        }
    }

}
