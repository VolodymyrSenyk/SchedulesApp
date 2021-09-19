package com.senyk.volodymyr.schedulesapp.presentation.core.customview

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.R.styleable
import com.senyk.volodymyr.schedulesapp.databinding.CustomTextInputLayoutBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.customview.MessageType.ERROR
import com.senyk.volodymyr.schedulesapp.presentation.core.customview.MessageType.WARNING
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.adapter.BaseDataBindingDelegationAdapter

class CustomTextInputLayout : ConstraintLayout {

    private lateinit var binding: CustomTextInputLayoutBinding

    private lateinit var messagesAdapter: BaseDataBindingDelegationAdapter

    private var clearButtonEnabled = false
    private var maxLength = 0
    private var errorMessage = ""
    private var warningMessage = ""
    private var required = false

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
        getCustomAttributes(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
        getCustomAttributes(context, attrs)
    }

    fun getText(): String = binding.inputField.text.toString()

    //fun setError(message: String) =

    private fun initView() {
        binding = CustomTextInputLayoutBinding.inflate(LayoutInflater.from(context), this, true)

        messagesAdapter = BaseDataBindingDelegationAdapter(
            listOf(MessageAdapterDelegate())
        )
        binding.listMessages.adapter = messagesAdapter

        binding.inputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val string = binding.inputField.text.toString()
                binding.buttonClear.isVisible = clearButtonEnabled && string.isNotEmpty()
                val messages = messagesAdapter.items.toMutableList()
                if (required) {
                    if (string.isEmpty()) {
                        messages.add(
                            MessageUi(
                                message = context.getString(R.string.message_empty_field),
                                messageType = ERROR
                            )
                        )
                    } else {
                        messages.removeAll {
                            val message = it as? MessageUi
                            message?.message == context.getString(R.string.message_empty_field)
                        }
                    }
                }

                if (maxLength != 0) {
                    if (string.length >= maxLength) {
                        messages.add(
                            MessageUi(
                                message = context.getString(R.string.message_edit_text_limit_reached),
                                messageType = WARNING
                            )
                        )
                    } else {
                        messages.removeAll {
                            val message = it as? MessageUi
                            message?.message == context.getString(R.string.message_edit_text_limit_reached)
                        }
                    }
                }

                messagesAdapter.items = messages
            }
        })
        binding.buttonClear.setOnClickListener { binding.inputField.setText("") }
    }

    private fun getCustomAttributes(ctx: Context, attrs: AttributeSet) {
        val typedArray = ctx.theme.obtainStyledAttributes(
            attrs,
            styleable.attrs_custom_text_input_layout,
            0,
            0
        )
        try {
            binding.inputField.inputType = typedArray.getInt(
                styleable.attrs_custom_text_input_layout_android_inputType,
                EditorInfo.TYPE_TEXT_VARIATION_NORMAL
            )
            binding.inputField.hint = typedArray.getString(styleable.attrs_custom_text_input_layout_android_hint)
            clearButtonEnabled = typedArray.getBoolean(
                styleable.attrs_custom_text_input_layout_clearButtonEnabled,
                false
            )
            required = typedArray.getBoolean(
                styleable.attrs_custom_text_input_layout_isRequired,
                false
            )
            maxLength = typedArray.getInt(
                styleable.attrs_custom_text_input_layout_android_maxLength,
                0
            )

            if (maxLength != 0) {
                val filterArray = arrayOfNulls<InputFilter>(1)
                filterArray[0] = InputFilter.LengthFilter(maxLength)
                binding.inputField.filters = filterArray
            }
            errorMessage = typedArray.getString(
                styleable.attrs_custom_text_input_layout_textError
            ) ?: ""
            warningMessage = typedArray.getString(
                styleable.attrs_custom_text_input_layout_textWarning
            ) ?: ""

            if (warningMessage.isNotEmpty()) {
                messagesAdapter.items = messagesAdapter.items.toMutableList().apply {
                    add(MessageUi(message = warningMessage, messageType = WARNING))
                }
            }
            if (errorMessage.isNotEmpty()) {
                messagesAdapter.items = messagesAdapter.items.toMutableList().apply {
                    add(MessageUi(message = errorMessage, messageType = ERROR))
                }
            }
        } finally {
            typedArray.recycle()
        }
    }
}
