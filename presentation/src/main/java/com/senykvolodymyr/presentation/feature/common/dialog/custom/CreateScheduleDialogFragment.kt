package com.senykvolodymyr.presentation.feature.common.dialog.custom

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.senykvolodymyr.core.base.dialog.BaseDialogFragment
import com.senykvolodymyr.core.extensions.setFragmentResult
import com.senykvolodymyr.presentation.R
import com.senykvolodymyr.presentation.databinding.DialogFragmentCreateScheduleBinding
import kotlinx.parcelize.Parcelize

class CreateScheduleDialogFragment : BaseDialogFragment<DialogFragmentCreateScheduleBinding>() {

    override val layoutRes = R.layout.dialog_fragment_create_schedule

    private var isFirstSchedule = true

    override fun initView() {
        binding.buttonDialogFilled.setOnClickListener {
            setFragmentResult(
                Create(
                    scheduleName = binding.inputScheduleName.getText(),
                    isSaturdayWorkingDay = binding.checkboxIsSaturdayWorking.isChecked,
                    isNumeratorDenominatorSystem = binding.checkboxIsNumeratorDenominator.isChecked
                )
            )
            dismiss()
        }

        binding.buttonDialogUnfilled.isVisible = !isFirstSchedule
        binding.buttonDialogUnfilled.setOnClickListener { dismiss() }
    }

    override fun initArguments(args: Bundle) {
        isFirstSchedule = args.getBoolean(BK_IS_FIRST_SCHEDULE, false)
    }

    @Parcelize data class Create(
        val scheduleName: String,
        val isSaturdayWorkingDay: Boolean,
        val isNumeratorDenominatorSystem: Boolean
    ) : Parcelable

    companion object {

        private const val BK_IS_FIRST_SCHEDULE = "BK_IS_FIRST_SCHEDULE"

        fun newInstance(isFirstSchedule: Boolean): CreateScheduleDialogFragment =
            CreateScheduleDialogFragment().apply {
                arguments = bundleOf(BK_IS_FIRST_SCHEDULE to isFirstSchedule)
            }
    }
}
