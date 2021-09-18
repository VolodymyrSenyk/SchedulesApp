package com.senyk.volodymyr.schedulesapp.presentation.feature.common.dialog

import android.os.Parcelable
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog.GeneralDialogFragment
import com.senyk.volodymyr.schedulesapp.presentation.core.extensions.setFragmentResult
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
