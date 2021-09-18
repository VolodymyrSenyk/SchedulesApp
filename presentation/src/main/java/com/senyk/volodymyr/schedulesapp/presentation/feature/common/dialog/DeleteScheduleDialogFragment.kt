package com.senyk.volodymyr.schedulesapp.presentation.feature.common.dialog

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog.GeneralDialogFragment
import com.senyk.volodymyr.schedulesapp.presentation.core.extensions.setFragmentResult
import kotlinx.parcelize.Parcelize

class DeleteScheduleDialogFragment : GeneralDialogFragment() {

    private lateinit var scheduleId: String
    private lateinit var scheduleName: String

    override val dialogFragmentContentInitializer = GeneralDialogFragmentContent(
        title = getString(R.string.dialog_delete_schedule_title),
        message = getString(
            R.string.dialog_delete_schedule_message, scheduleName
        ),
        dialogIconData = GeneralDialogFragmentIconData(
            drawableAttrRes = R.attr.iconWarning,
            tintColorAttrRes = R.attr.colorWarning
        ),
        filledButtonData = GeneralDialogFragmentButtonData(
            text = getString(R.string.dialog_answer_continue),
            clickListener = { setFragmentResult(Confirm(scheduleId)); dismiss() }
        ),
        unfilledButtonData = GeneralDialogFragmentButtonData(
            text = getString(R.string.dialog_answer_cancel),
            clickListener = { dismiss() }
        )
    )

    override fun initArguments(args: Bundle) {
        scheduleId = args.getString(BK_SCHEDULE_ID, "")
        scheduleName = args.getString(BK_SCHEDULE_NAME, "")
    }

    @Parcelize data class Confirm(val scheduleId: String) : Parcelable

    companion object {

        private const val BK_SCHEDULE_ID = "BK_SCHEDULE_ID"
        private const val BK_SCHEDULE_NAME = "BK_SCHEDULE_NAME"

        fun newInstance(scheduleId: String, scheduleName: String): DeleteScheduleDialogFragment =
            DeleteScheduleDialogFragment().apply {
                arguments = bundleOf(
                    BK_SCHEDULE_ID to scheduleId,
                    BK_SCHEDULE_NAME to scheduleName
                )
            }
    }
}
