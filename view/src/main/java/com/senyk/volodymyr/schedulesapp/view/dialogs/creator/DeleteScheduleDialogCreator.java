package com.senyk.volodymyr.schedulesapp.view.dialogs.creator;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialogs.builder.AlertDialogBuilder;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.DialogClickListener;

public class DeleteScheduleDialogCreator implements DialogCreator {
    private DialogClickListener listener;
    private String scheduleName;

    public DeleteScheduleDialogCreator(DialogClickListener listener, String scheduleName) {
        this.listener = listener;
        this.scheduleName = scheduleName;
    }

    @Override
    public AlertDialog createDialog(Context context) {
        return new AlertDialogBuilder(context)
                .addTitle(context.getString(R.string.delete_schedule_dialog_title))
                .addIcon(R.drawable.ic_warning)
                .addMessage(context.getString(R.string.delete_schedule_dialog_message))
                .addPositiveButton(
                        context.getString(R.string.answer_yes),
                        (dialogInterface, i) -> listener.onPositiveButtonClick(
                                DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG,
                                scheduleName))
                .addNegativeButton(
                        context.getString(R.string.answer_no),
                        (dialogInterface, i) -> listener.onNegativeButtonClick(
                                DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG,
                                ""))
                .build();
    }
}
