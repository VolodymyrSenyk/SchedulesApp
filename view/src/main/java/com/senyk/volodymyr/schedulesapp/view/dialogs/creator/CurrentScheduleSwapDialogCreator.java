package com.senyk.volodymyr.schedulesapp.view.dialogs.creator;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.dialogs.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialogs.builder.AlertDialogBuilder;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.DialogClickListener;

public class CurrentScheduleSwapDialogCreator implements DialogCreator {
    private DialogClickListener listener;

    public CurrentScheduleSwapDialogCreator(DialogClickListener listener) {
        this.listener = listener;
    }

    @Override
    public AlertDialog createDialog(Context context) {
        return new AlertDialogBuilder(context)
                .addTitle(context.getString(R.string.schedule_swap_dialog_title))
                .addIcon(R.drawable.ic_warning)
                .addMessage(context.getString(R.string.schedule_swap_dialog_message))
                .addNegativeButton(
                        context.getString(R.string.answer_no),
                        (dialogInterface, i) -> listener.onNegativeButtonClick(
                                DialogFragmentsTypes.CURRENT_SCHEDULE_SWAP_DIALOG,
                                ""
                        ))
                .addPositiveButton(
                        context.getString(R.string.answer_yes),
                        (dialogInterface, i) -> listener.onPositiveButtonClick(
                                DialogFragmentsTypes.CURRENT_SCHEDULE_SWAP_DIALOG,
                                ""
                        ))
                .build();
    }
}
