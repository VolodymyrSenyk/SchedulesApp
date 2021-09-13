package com.senyk.volodymyr.schedulesapp.presentation.core.extensions

inline val Boolean?.int get() = if (this == true) 1 else 0
