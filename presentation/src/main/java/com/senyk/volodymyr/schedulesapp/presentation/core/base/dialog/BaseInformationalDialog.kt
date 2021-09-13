package com.senyk.volodymyr.schedulesapp.presentation.core.base.dialog

import androidx.annotation.CallSuper
import androidx.core.view.isVisible
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.AttrValuesProvisionUtil

abstract class BaseInformationalDialog : BaseDialog() {

    @CallSuper
    override fun initView() {
        iconDialog.setImageDrawable(
            AttrValuesProvisionUtil.getThemeDrawable(
                requireContext().theme, R.attr.iconInfo
            )
        )
        iconDialog.drawable.setTint(
            AttrValuesProvisionUtil.getThemeColorInt(
                requireContext().theme, R.attr.colorContentPrimary
            )
        )
        buttonFilled.isVisible = false
        buttonUnfilled.text = getString(R.string.dialog_answer_ok)
        buttonUnfilled.setOnClickListener { dismiss() }
    }
}
