package com.senykvolodymyr.core.customview

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.senykvolodymyr.core.R
import com.senykvolodymyr.core.customview.MessageType.ERROR
import com.senykvolodymyr.core.customview.MessageType.WARNING
import com.senykvolodymyr.core.databinding.CustomTextInputLayoutBinding
import com.senykvolodymyr.core.recyclerview.adapter.BaseDataBindingDelegationAdapter

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

    fun getText(): String = binding.editTextInputField.text.toString()

    //fun setError(message: String) =

    private fun initView() {
        binding = CustomTextInputLayoutBinding.inflate(LayoutInflater.from(context), this, true)

        messagesAdapter = BaseDataBindingDelegationAdapter(
            listOf(MessageAdapterDelegate())
        )
        binding.listMessages.adapter = messagesAdapter

        binding.editTextInputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val string = binding.editTextInputField.text.toString()
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
        binding.buttonClear.setOnClickListener { binding.editTextInputField.setText("") }
    }

    private fun getCustomAttributes(ctx: Context, attrs: AttributeSet) {
        val typedArray = ctx.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTextInputLayout,
            0,
            0
        )
        try {
            binding.editTextInputField.inputType = typedArray.getInt(
                R.styleable.CustomTextInputLayout_android_inputType,
                EditorInfo.TYPE_TEXT_VARIATION_NORMAL
            )
            binding.editTextInputField.hint = typedArray.getString(R.styleable.CustomTextInputLayout_android_hint)
            clearButtonEnabled = typedArray.getBoolean(
                R.styleable.CustomTextInputLayout_clearButtonEnabled,
                false
            )
            required = typedArray.getBoolean(
                R.styleable.CustomTextInputLayout_isRequired,
                false
            )
            maxLength = typedArray.getInt(
                R.styleable.CustomTextInputLayout_android_maxLength,
                0
            )

            if (maxLength != 0) {
                val filterArray = arrayOfNulls<InputFilter>(1)
                filterArray[0] = InputFilter.LengthFilter(maxLength)
                binding.editTextInputField.filters = filterArray
            }
            errorMessage = typedArray.getString(
                R.styleable.CustomTextInputLayout_textError
            ) ?: ""
            warningMessage = typedArray.getString(
                R.styleable.CustomTextInputLayout_textWarning
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
