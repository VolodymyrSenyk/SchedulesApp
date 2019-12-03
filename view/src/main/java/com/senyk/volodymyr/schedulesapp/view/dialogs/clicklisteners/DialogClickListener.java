package com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners;

public interface DialogClickListener extends DialogFragmentClickListener {
    void onPositiveButtonClick(String dialogType, String additionalData);

    void onNegativeButtonClick(String dialogType, String additionalData);
}
