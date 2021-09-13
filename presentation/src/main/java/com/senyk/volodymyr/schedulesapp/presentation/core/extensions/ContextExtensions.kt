package com.senyk.volodymyr.schedulesapp.presentation.core.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager

inline val Context.inputMethodManager: InputMethodManager
    get() = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

inline val Context.layoutInflater: LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
