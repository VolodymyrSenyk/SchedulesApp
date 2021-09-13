package com.senyk.volodymyr.schedulesapp.presentation.feature.common.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.senyk.volodymyr.schedulesapp.R

class EnterPasswordDialogFragment : DialogFragment() {

    private var onButtonClickListener: OnButtonClickListener? = null

    private lateinit var inputPassword: EditText
    private lateinit var groupLimitReached: Group
    private lateinit var buttonFilled: MaterialButton
    private lateinit var buttonUnfilled: MaterialButton

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onButtonClickListener = if (targetFragment != null) {
            targetFragment as OnButtonClickListener
        } else {
            activity as OnButtonClickListener
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(false)
            isCancelable = false
        }

    override fun onStart() {
        super.onStart()
        dialog?.let { thisDialog ->
            thisDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
          //  inputPassword = thisDialog.findViewById(R.id.input_backup_password)
         //   groupLimitReached = thisDialog.findViewById(R.id.group_backup_password_limit_reached)
         //   buttonFilled = thisDialog.findViewById(R.id.button_dialog_filled)
         //   buttonUnfilled = thisDialog.findViewById(R.id.button_dialog_unfilled)
        }
        initViews()
    }

  /*  override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_fragment_enter_password, container, false)*/

    private fun initViews() {
        buttonFilled.setOnClickListener {
            onButtonClickListener?.onConfirmPasswordClickListener(inputPassword.text.toString())
            dismiss()
        }
        buttonUnfilled.setOnClickListener { dismiss() }
        groupLimitReached.isVisible = false
        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
           //     groupLimitReached.isVisible =
          //          s.toString().length >= resources.getInteger(R.integer.max_length_backup_password)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDetach() {
        super.onDetach()
        onButtonClickListener = null
    }

    interface OnButtonClickListener {
        fun onConfirmPasswordClickListener(password: String)
    }

    companion object {
        fun newInstance(): EnterPasswordDialogFragment = EnterPasswordDialogFragment()
    }
}
