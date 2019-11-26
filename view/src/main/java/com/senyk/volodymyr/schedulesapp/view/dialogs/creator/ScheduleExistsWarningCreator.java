package com.senyk.volodymyr.schedulesapp.view.dialogs.creator;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialogs.builder.AlertDialogBuilder;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.WarningClickListener;

public class ScheduleExistsWarningCreator implements DialogCreator {
    private WarningClickListener listener;

    public ScheduleExistsWarningCreator(WarningClickListener listener) {
        this.listener = listener;
    }

    @Override
    public AlertDialog createDialog(Context context) {
        return new AlertDialogBuilder(context)
                .addTitle(context.getString(R.string.schedule_exists_dialog_title))
                .addMessage(context.getString(R.string.schedule_exists_dialog_message))
                .addNegativeButton(
                        context.getString(R.string.answer_ok),
                        (dialogInterface, i) -> listener.onNeutralButtonClick(DialogFragmentsTypes.SCHEDULE_ALREADY_EXISTS_WARNING))
                .build();
    }
}
