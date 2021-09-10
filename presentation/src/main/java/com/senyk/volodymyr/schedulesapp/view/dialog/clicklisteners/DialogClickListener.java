package com.senyk.volodymyr.schedulesapp.view.dialog.clicklisteners;

public interface DialogClickListener extends DialogFragmentClickListener {
    void onPositiveButtonClick(String dialogType, String additionalData);

    void onNegativeButtonClick(String dialogType, String additionalData);
}
