package com.senykvolodymyr.core.base.dialog

import androidx.annotation.AttrRes
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import com.senykvolodymyr.core.R
import com.senykvolodymyr.core.databinding.DialogFragmentBaseBinding
import com.senykvolodymyr.core.provider.AttrValuesProvisionUtil

abstract class GeneralDialogFragment : BaseDialogFragment<DialogFragmentBaseBinding>() {

    @LayoutRes override val layoutRes = R.layout.dialog_fragment_base

    protected val dialogFragmentContent: DialogFragmentContent by lazy { dialogFragmentContentInitializer }

    abstract val dialogFragmentContentInitializer: DialogFragmentContent

    @CallSuper
    override fun initView() {
        if (dialogFragmentContent !is GeneralDialogFragmentContent) return
        with(dialogFragmentContent as GeneralDialogFragmentContent) {
            binding.textDialogTitle.text = title
            binding.textDialogMessage.text = message

            dialogIconData?.let {
                binding.iconDialogTitle.setImageDrawable(
                    AttrValuesProvisionUtil.getThemeDrawable(
                        requireContext().theme,
                        it.drawableAttrRes
                    )
                )
                binding.iconDialogTitle.drawable.setTint(
                    AttrValuesProvisionUtil.getThemeColorInt(
                        requireContext().theme,
                        it.tintColorAttrRes
                    )
                )
            }

            binding.buttonDialogFilled.isVisible = filledButtonData != null
            filledButtonData?.let {
                binding.buttonDialogFilled.text = it.text
                binding.buttonDialogFilled.setOnClickListener { _ -> it.clickListener }
            }

            binding.buttonDialogUnfilled.isVisible = filledButtonData != null
            unfilledButtonData?.let {
                binding.buttonDialogUnfilled.text = it.text
                binding.buttonDialogUnfilled.setOnClickListener { _ -> it.clickListener }
            }
        }
    }

    interface DialogFragmentContent

    data class GeneralDialogFragmentContent(
        val title: String,
        val dialogIconData: GeneralDialogFragmentIconData?,
        val message: String,
        val filledButtonData: GeneralDialogFragmentButtonData?,
        val unfilledButtonData: GeneralDialogFragmentButtonData?,
    ) : DialogFragmentContent

    data class GeneralDialogFragmentIconData(
        @AttrRes val drawableAttrRes: Int,
        @AttrRes val tintColorAttrRes: Int
    )

    data class GeneralDialogFragmentButtonData(
        val text: String,
        val clickListener: () -> Unit
    )
}
