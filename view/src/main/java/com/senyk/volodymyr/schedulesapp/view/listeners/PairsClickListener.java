package com.senyk.volodymyr.schedulesapp.view.listeners;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

public interface PairsClickListener {
    void pairAddButtonClicked();

    void pairDeleteButtonClicked(PairUi pair);
}
