package com.senyk.volodymyr.schedulesapp.presentation.feature.common.dialog

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.DialogFragmentCreateScheduleBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog.BaseDialogFragment
import com.senyk.volodymyr.schedulesapp.presentation.core.extensions.setFragmentResult
import kotlinx.parcelize.Parcelize

class CreateScheduleDialogFragment : BaseDialogFragment<DialogFragmentCreateScheduleBinding>() {

    override val layoutRes = R.layout.dialog_fragment_create_schedule

    private var isFirstSchedule = true

    override fun initView() {
        binding.buttonDialogFilled.setOnClickListener {
            setFragmentResult(
                Create(
                    scheduleName = binding.inputScheduleName.text.toString(),
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
