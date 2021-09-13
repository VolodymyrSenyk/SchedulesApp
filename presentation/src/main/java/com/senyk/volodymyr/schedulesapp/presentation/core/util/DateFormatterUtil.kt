package com.senyk.volodymyr.schedulesapp.presentation.core.util

import java.text.DateFormat
import java.util.*

object DateFormatterUtil {

    fun getShortDateAndTime(date: Long, locale: Locale = Locale.ROOT): String =
        "${getShortDate(date, locale)} ${getShortTime(date, locale)}"

    fun getShortDateAndTime(date: Date, locale: Locale = Locale.ROOT): String =
        getShortDateAndTime(date.time, locale)

    fun getShortDate(date: Long, locale: Locale = Locale.ROOT): String =
        DateFormat.getDateInstance(DateFormat.SHORT, locale).format(date)

    fun getShortDate(date: Date, locale: Locale = Locale.ROOT): String =
        getShortDate(date.time, locale)

    fun getShortTime(date: Long, locale: Locale = Locale.ROOT): String =
        DateFormat.getTimeInstance(DateFormat.SHORT, locale).format(date)

    fun getShortTime(date: Date, locale: Locale = Locale.ROOT): String =
        getShortTime(date.time, locale)
}
