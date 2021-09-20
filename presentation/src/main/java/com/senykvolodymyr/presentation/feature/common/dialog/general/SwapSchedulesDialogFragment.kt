package com.senykvolodymyr.presentation.feature.common.dialog.general

import android.os.Parcelable
import com.senykvolodymyr.core.base.dialog.GeneralDialogFragment
import com.senykvolodymyr.core.extensions.setFragmentResult
import com.senykvolodymyr.presentation.R
import kotlinx.parcelize.Parcelize

class SwapSchedulesDialogFragment : GeneralDialogFragment() {

    override val dialogFragmentContentInitializer = GeneralDialogFragmentContent(
        title = getString(R.string.dialog_swap_schedules_title),
        message = getString(R.string.dialog_swap_schedules_message),
        dialogIconData = GeneralDialogFragmentIconData(
            drawableAttrRes = R.attr.iconInfo,
            tintColorAttrRes = R.attr.colorContentPrimary
        ),
        filledButtonData = GeneralDialogFragmentButtonData(
            text = getString(R.string.dialog_answer_continue),
            clickListener = { setFragmentResult(Confirm); dismiss() }
        ),
        unfilledButtonData = GeneralDialogFragmentButtonData(
            text = getString(R.string.dialog_answer_cancel),
            clickListener = { dismiss() }
        )
    )

    @Parcelize object Confirm : Parcelable

    companion object {
        fun newInstance(): SwapSchedulesDialogFragment = SwapSchedulesDialogFragment()
    }
}
