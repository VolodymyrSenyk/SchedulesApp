package com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity

import androidx.annotation.StringRes
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType

enum class DayTypeUi(@StringRes val nameResId: Int) {
    MONDAY(R.string.day_of_the_week_monday),
    TUESDAY(R.string.day_of_the_week_tuesday),
    WEDNESDAY(R.string.day_of_the_week_wednesday),
    THURSDAY(R.string.day_of_the_week_thursday),
    FRIDAY(R.string.day_of_the_week_friday),
    SATURDAY(R.string.day_of_the_week_saturday)
}

fun DayType.toDayTypeUi(): DayTypeUi = when (this) {
    DayType.MONDAY -> DayTypeUi.MONDAY
    DayType.TUESDAY -> DayTypeUi.TUESDAY
    DayType.WEDNESDAY -> DayTypeUi.WEDNESDAY
    DayType.THURSDAY -> DayTypeUi.THURSDAY
    DayType.FRIDAY -> DayTypeUi.FRIDAY
    DayType.SATURDAY -> DayTypeUi.SATURDAY
}

fun DayTypeUi.toDayType(): DayType = when (this) {
    DayTypeUi.MONDAY -> DayType.MONDAY
    DayTypeUi.TUESDAY -> DayType.TUESDAY
    DayTypeUi.WEDNESDAY -> DayType.WEDNESDAY
    DayTypeUi.THURSDAY -> DayType.THURSDAY
    DayTypeUi.FRIDAY -> DayType.FRIDAY
    DayTypeUi.SATURDAY -> DayType.SATURDAY
}
