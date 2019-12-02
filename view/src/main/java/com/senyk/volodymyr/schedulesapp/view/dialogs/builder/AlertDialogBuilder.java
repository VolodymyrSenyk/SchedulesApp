package com.senyk.volodymyr.schedulesapp.view.dialogs.builder;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.senyk.volodymyr.schedulesapp.R;

public class AlertDialogBuilder {
    private AlertDialog.Builder builder;

    public AlertDialogBuilder(Context context) {
        this.builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
    }

    public AlertDialog build() {
        return builder.create();
    }

    public AlertDialogBuilder addTitle(String title) {
        builder.setTitle(title);
        return this;
    }

    public AlertDialogBuilder addMessage(String message) {
        builder.setMessage(message);
        return this;
    }

    public AlertDialogBuilder addPositiveButton(String text, DialogInterface.OnClickListener listener) {
        builder.setPositiveButton(text, listener);
        return this;
    }

    public AlertDialogBuilder addNegativeButton(String text, DialogInterface.OnClickListener listener) {
        builder.setNegativeButton(text, listener);
        return this;
    }

    public AlertDialogBuilder addNeutralButton(String text, DialogInterface.OnClickListener listener) {
        builder.setNeutralButton(text, listener);
        return this;
    }

    public AlertDialogBuilder addIcon(int iconId) {
        builder.setIcon(iconId);
        return this;
    }
}
