package com.senyk.volodymyr.schedulesapp.presentation.core.extensions

inline fun <reified E : Enum<E>> Int.toEnum(): E = enumValues<E>()[this]

inline fun <reified E : Enum<E>> E.toInt(): Int = this.ordinal
