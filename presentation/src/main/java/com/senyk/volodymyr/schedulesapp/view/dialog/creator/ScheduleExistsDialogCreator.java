package com.senyk.volodymyr.schedulesapp.view.dialog.creator;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.dialog.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialog.builder.AlertDialogBuilder;
import com.senyk.volodymyr.schedulesapp.view.dialog.clicklisteners.WarningClickListener;

public class ScheduleExistsDialogCreator implements DialogCreator {
    private WarningClickListener listener;

    public ScheduleExistsDialogCreator(WarningClickListener listener) {
        this.listener = listener;
    }

    @Override
    public AlertDialog createDialog(Context context) {
        return new AlertDialogBuilder(context)
                .addTitle(context.getString(R.string.schedule_exists_dialog_title))
                .addIcon(R.drawable.ic_error)
                .addMessage(context.getString(R.string.schedule_exists_dialog_message))
                .addNeutralButton(
                        context.getString(R.string.answer_ok),
                        (dialogInterface, i) -> listener.onNeutralButtonClick(DialogFragmentsTypes.SCHEDULE_EXISTS_ERROR))
                .build();
    }
}
