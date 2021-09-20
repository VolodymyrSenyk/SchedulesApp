package com.senykvolodymyr.core.extensions

inline val Boolean?.int get() = if (this == true) 1 else 0
