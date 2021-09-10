package com.senyk.volodymyr.schedulesapp.view.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.senyk.volodymyr.schedulesapp.R;

public class CustomTextInputField extends TextInputEditText {
    public CustomTextInputField(Context context) {
        super(context);
    }

    public CustomTextInputField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextInputField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Drawable getBackground() {
        return ContextCompat.getDrawable(getContext(), R.drawable.ic_back);
    }
}
