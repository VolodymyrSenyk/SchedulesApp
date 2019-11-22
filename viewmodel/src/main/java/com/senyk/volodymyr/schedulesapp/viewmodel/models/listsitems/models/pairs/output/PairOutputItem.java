package com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.pairs.output;

import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.markers.PairOutput;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

public class PairOutputItem extends PairUi implements PairOutput {
    public PairOutputItem(PairUi pair) {
        super(
                pair.getTime(),
                pair.getName(),
                pair.getTeacher(),
                pair.getType(),
                pair.getPlace(),
                pair.getAdditionalInfo(),
                pair.getHolderColor()
        );
    }
}
