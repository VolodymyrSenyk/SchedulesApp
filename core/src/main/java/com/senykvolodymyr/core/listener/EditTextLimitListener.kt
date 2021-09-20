package com.senykvolodymyr.core.listener

import android.text.Editable
import android.text.TextWatcher

class EditTextLimitListener(
    private val limit: Int,
    private val onLimitReachedCallback: () -> Unit
) : TextWatcher {

    private var callbackAlreadyInvoked: Boolean = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.toString().length >= limit && !callbackAlreadyInvoked) {
            onLimitReachedCallback()
            callbackAlreadyInvoked = true
        } else {
            callbackAlreadyInvoked = false
        }
    }

    override fun afterTextChanged(s: Editable?) {}
}
