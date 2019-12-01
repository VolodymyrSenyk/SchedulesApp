package com.senyk.volodymyr.schedulesapp.viewmodel.models.ui;

import java.util.List;

public class DayUi {
    private int ordinal;
    private int weekOrdinal;
    private List<PairUi> pairs;

    public int getOrdinal() {
        return this.ordinal;
    }

    public int getWeekOrdinal() {
        return this.weekOrdinal;
    }

    public List<PairUi> getPairs() {
        return this.pairs;
    }

    public DayUi(int ordinal, int weekOrdinal, List<PairUi> pairs) {
        this.ordinal = ordinal;
        this.weekOrdinal = weekOrdinal;
        this.pairs = pairs;
    }
}
