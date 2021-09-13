package com.senyk.volodymyr.schedulesapp.presentation.feature.common.dialog

import android.content.Context
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog.BaseChoiceDialog

class ExitWithoutSaveDialogFragment : BaseChoiceDialog() {

    private var onButtonClickListener: OnButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onButtonClickListener = if (targetFragment != null) {
            targetFragment as OnButtonClickListener
        } else {
            activity as OnButtonClickListener
        }
    }

    override fun initView() {
        super.initView()
        outputTitle.text = ""//getString(R.string.dialog_exit_without_save_title)
        outputMessage.text = ""//getString(R.string.dialog_exit_without_save_message)
        buttonFilled.setOnClickListener {
            onButtonClickListener?.onConfirmExitWithoutSaveClickListener()
            dismiss()
        }
    }

    override fun onDetach() {
        super.onDetach()
        onButtonClickListener = null
    }

    interface OnButtonClickListener {
        fun onConfirmExitWithoutSaveClickListener()
    }

    companion object {
        fun newInstance(): ExitWithoutSaveDialogFragment = ExitWithoutSaveDialogFragment()
    }
}
