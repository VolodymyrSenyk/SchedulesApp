package com.senyk.volodymyr.schedulesapp.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.DialogClickListener;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.DialogFragmentClickListener;
import com.senyk.volodymyr.schedulesapp.view.dialogs.clicklisteners.WarningClickListener;
import com.senyk.volodymyr.schedulesapp.view.dialogs.creator.CurrentScheduleSwapDialogCreator;
import com.senyk.volodymyr.schedulesapp.view.dialogs.creator.ScheduleDeletingDialogCreator;
import com.senyk.volodymyr.schedulesapp.view.dialogs.creator.ScheduleExistsWarningCreator;
import com.senyk.volodymyr.schedulesapp.view.exceptions.NoSuchDialogFragmentClassException;

public class DialogFragmentFactory extends DialogFragment {
    public static final String FRAGMENT_DIALOG_TYPE_ARGS_KEY = "DialogType";
    public static final String ADDITIONAL_DATA_ARGS_KEY = "DialogAdditionalData";

    private DialogFragmentClickListener onClickListener;

    public static DialogFragmentFactory newInstance(String dialogType) {
        DialogFragmentFactory fragment = new DialogFragmentFactory();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_DIALOG_TYPE_ARGS_KEY, dialogType);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogFragmentFactory newInstance(
            String dialogType,
            String additionalData
    ) {
        DialogFragmentFactory fragment = new DialogFragmentFactory();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_DIALOG_TYPE_ARGS_KEY, dialogType);
        if (additionalData != null) args.putString(ADDITIONAL_DATA_ARGS_KEY, additionalData);
        fragment.setArguments(args);
        return fragment;
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
            String dialogType = getArguments().getString(FRAGMENT_DIALOG_TYPE_ARGS_KEY);
            if (dialogType == null) {
                throw new NoSuchDialogFragmentClassException();
            }
            switch (dialogType) {
                case DialogFragmentsTypes.CURRENT_SCHEDULE_SWAP_DIALOG:
                    return new CurrentScheduleSwapDialogCreator((DialogClickListener) onClickListener)
                            .createDialog(requireContext());
                case DialogFragmentsTypes.SCHEDULE_DELETING_DIALOG:
                    return new ScheduleDeletingDialogCreator(
                            (DialogClickListener) onClickListener,
                            getArguments().getString(ADDITIONAL_DATA_ARGS_KEY)
                    )
                            .createDialog(requireContext());
                case DialogFragmentsTypes.SCHEDULE_ALREADY_EXISTS_WARNING:
                    return new ScheduleExistsWarningCreator((WarningClickListener) onClickListener)
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
