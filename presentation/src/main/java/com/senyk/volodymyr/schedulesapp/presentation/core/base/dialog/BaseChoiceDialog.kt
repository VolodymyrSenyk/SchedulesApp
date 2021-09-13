package com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog

import androidx.annotation.CallSuper
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.AttrValuesProvisionUtil

abstract class BaseChoiceDialog : BaseDialog() {

    @CallSuper
    override fun initView() {
        iconDialog.setImageDrawable(
            AttrValuesProvisionUtil.getThemeDrawable(
                requireContext().theme, R.attr.iconWarning
            )
        )
        iconDialog.drawable.setTint(
            AttrValuesProvisionUtil.getThemeColorInt(
                requireContext().theme, R.attr.colorWarning
            )
        )
        buttonFilled.text = getString(R.string.dialog_answer_continue)
        buttonUnfilled.text = getString(R.string.dialog_answer_cancel)
        buttonUnfilled.setOnClickListener { dismiss() }
    }
}
