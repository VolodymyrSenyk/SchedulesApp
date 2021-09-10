package com.senyk.volodymyr.schedulesapp.view.adapterdelegate.listener;

import com.senyk.volodymyr.schedulesapp.view.entity.PairUi;

public interface PairsClickListener {
    void pairAddButtonClicked();

    void pairDeleteButtonClicked(PairUi pair);
}
