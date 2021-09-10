package com.senyk.volodymyr.schedulesapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.schedulesapp.view.dialog.clicklisteners.DialogClickListener;
import com.senyk.volodymyr.schedulesapp.view.dialog.clicklisteners.DialogFragmentClickListener;
import com.senyk.volodymyr.schedulesapp.view.dialog.clicklisteners.WarningClickListener;
import com.senyk.volodymyr.schedulesapp.view.dialog.creator.DeleteScheduleDialogCreator;
import com.senyk.volodymyr.schedulesapp.view.dialog.creator.ScheduleExistsDialogCreator;
import com.senyk.volodymyr.schedulesapp.view.dialog.creator.SwapSchedulesDialogCreator;
import com.senyk.volodymyr.schedulesapp.view.exception.NoSuchDialogFragmentClassException;

public class DialogFragmentFactory extends DialogFragment {
    private static final String FRAGMENT_DIALOG_TYPE_BUNDLE_KEY = "DialogType";
    private static final String ADDITIONAL_DATA_BUNDLE_KEY = "DialogAdditionalData";

    private DialogFragmentClickListener onClickListener;

    public static DialogFragmentFactory newInstance(String dialogType) {
        DialogFragmentFactory newDialogFragmentFactory = new DialogFragmentFactory();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_DIALOG_TYPE_BUNDLE_KEY, dialogType);
        newDialogFragmentFactory.setArguments(args);
        return newDialogFragmentFactory;
    }

    public static DialogFragmentFactory newInstance(
            String dialogType,
            String additionalData) {
        DialogFragmentFactory newDialogFragmentFactory = new DialogFragmentFactory();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_DIALOG_TYPE_BUNDLE_KEY, dialogType);
        if (additionalData != null) args.putString(ADDITIONAL_DATA_BUNDLE_KEY, additionalData);
        newDialogFragmentFactory.setArguments(args);
        return newDialogFragmentFactory;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.onClickListener = (DialogFragmentClickListener) getTargetFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new NoSuchDialogFragmentClassException();
        } else {
            String dialogType = getArguments().getString(FRAGMENT_DIALOG_TYPE_BUNDLE_KEY);
            if (dialogType == null) {
                throw new NoSuchDialogFragmentClassException();
            }
            switch (dialogType) {
                case DialogFragmentsTypes.SWAP_SCHEDULES_DIALOG:
                    return new SwapSchedulesDialogCreator((DialogClickListener) onClickListener)
                            .createDialog(requireContext());
                case DialogFragmentsTypes.DELETE_SCHEDULE_DIALOG:
                    return new DeleteScheduleDialogCreator(
                            (DialogClickListener) onClickListener,
                            getArguments().getString(ADDITIONAL_DATA_BUNDLE_KEY))
                            .createDialog(requireContext());
                case DialogFragmentsTypes.SCHEDULE_EXISTS_ERROR:
                    return new ScheduleExistsDialogCreator((WarningClickListener) onClickListener)
                            .createDialog(requireContext());
                default:
                    throw new NoSuchDialogFragmentClassException();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.onClickListener = null;
    }
}
