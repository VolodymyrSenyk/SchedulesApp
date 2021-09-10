package com.senyk.volodymyr.schedulesapp.view.dialog.creator;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.dialog.DialogFragmentsTypes;
import com.senyk.volodymyr.schedulesapp.view.dialog.builder.AlertDialogBuilder;
import com.senyk.volodymyr.schedulesapp.view.dialog.clicklisteners.DialogClickListener;

public class SwapSchedulesDialogCreator implements DialogCreator {
    private DialogClickListener listener;

    public SwapSchedulesDialogCreator(DialogClickListener listener) {
        this.listener = listener;
    }

    @Override
    public AlertDialog createDialog(Context context) {
        return new AlertDialogBuilder(context)
                .addTitle(context.getString(R.string.swap_schedules_dialog_title))
                .addIcon(R.drawable.ic_warning)
                .addMessage(context.getString(R.string.swap_schedules_dialog_message))
                .addNegativeButton(
                        context.getString(R.string.answer_no),
                        (dialogInterface, i) -> listener.onNegativeButtonClick(
                                DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG,
                                ""))
                .addPositiveButton(
                        context.getString(R.string.answer_yes),
                        (dialogInterface, i) -> listener.onPositiveButtonClick(
                                DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG,
                                ""))
                .build();
    }
}
