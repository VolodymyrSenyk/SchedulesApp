package com.senyk.volodymyr.schedulesapp.presentation.feature.common.dialog

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog.BaseChoiceDialog

class DeleteScheduleDialogFragment : BaseChoiceDialog() {

    private var onButtonClickListener: OnButtonClickListener? = null
    private lateinit var noteId: String
    private lateinit var noteName: String

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
     //   outputTitle.text = getString(R.string.dialog_delete_note_title)
    //    outputMessage.text = getString(
    //        R.string.dialog_delete_note_message,
   //         if (noteName.isEmpty()) "" else getString(R.string.name_pattern, noteName)
    //    )
        buttonFilled.setOnClickListener {
            onButtonClickListener?.onConfirmNoteDeleteClickListener(noteId = noteId)
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            noteId = args.get(BK_NOTE_ID).toString()
            noteName = args.get(BK_NOTE_NAME).toString()
        }
    }

    override fun onDetach() {
        super.onDetach()
        onButtonClickListener = null
    }

    interface OnButtonClickListener {
        fun onConfirmNoteDeleteClickListener(noteId: String)
    }

    companion object {
        private const val BK_NOTE_ID = "BK_NOTE_ID"
        private const val BK_NOTE_NAME = "BK_NOTE_NAME"

        fun newInstance(noteId: String, noteName: String): DeleteScheduleDialogFragment =
            DeleteScheduleDialogFragment().apply {
                arguments = bundleOf(
                    BK_NOTE_ID to noteId,
                    BK_NOTE_NAME to noteName
                )
            }
    }
}
