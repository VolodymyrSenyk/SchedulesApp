package com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.senyk.volodymyr.schedulesapp.R

abstract class BaseDialog : DialogFragment() {

    protected lateinit var iconDialog: ImageView
    protected lateinit var outputTitle: MaterialTextView
    protected lateinit var outputMessage: MaterialTextView
    protected lateinit var buttonFilled: MaterialButton
    protected lateinit var buttonUnfilled: MaterialButton

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(false)
            isCancelable = false
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_fragment_base, container, false)

    override fun onStart() {
        super.onStart()
        dialog?.let { thisDialog ->
            thisDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            iconDialog = thisDialog.findViewById(R.id.icon_dialog_title)
            outputTitle = thisDialog.findViewById(R.id.output_dialog_title)
            outputMessage = thisDialog.findViewById(R.id.output_dialog_message)
            buttonFilled = thisDialog.findViewById(R.id.button_dialog_filled)
            buttonUnfilled = thisDialog.findViewById(R.id.button_dialog_unfilled)

            initView()
        }
    }

    abstract fun initView()
}
