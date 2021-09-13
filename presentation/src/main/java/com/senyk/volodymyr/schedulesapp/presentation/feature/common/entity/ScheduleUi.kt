package com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity

import androidx.annotation.ColorInt
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleInfo
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.ResourcesProvider
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.ListItem
import com.senyk.volodymyr.schedulesapp.presentation.core.util.DateFormatterUtil

data class ScheduleUi(
    val id: String,
    val name: String,
    val createdAtInMillis: Long,
    val updatedAtInMillis: Long,
    val createdAt: String,
    val updatedAt: String,
    val isSaturdayWorkingDay: Boolean,
    val isNumeratorDenominatorSystem: Boolean,
    val isCurrent: Boolean,
    @ColorInt val tintColorInt: Int
) : ListItem {
    override val viewType: Int = this::class.hashCode()
    override val listId: String = System.currentTimeMillis().toString()
}

internal fun ScheduleInfo.toScheduleUi(resourcesProvider: ResourcesProvider): ScheduleUi =
    ScheduleUi(
        id = id,
        name = name,
        createdAtInMillis = createdAtInMillis,
        updatedAtInMillis = updatedAtInMillis,
        createdAt = resourcesProvider.getString(
            R.string.schedule_date_of_creation_output, DateFormatterUtil.getShortDateAndTime(
                createdAtInMillis, resourcesProvider.getCurrentLocale()
            )
        ),
        updatedAt = resourcesProvider.getString(
            R.string.schedule_date_of_update_output, DateFormatterUtil.getShortDateAndTime(
                updatedAtInMillis, resourcesProvider.getCurrentLocale()
            )
        ),
        isSaturdayWorkingDay = isSaturdayWorkingDay,
        isNumeratorDenominatorSystem = isNumeratorDenominatorSystem,
        isCurrent = isCurrent,
        tintColorInt = resourcesProvider.getColorInt(
            if (isCurrent) R.color.colorSelected else R.color.colorNonSelected
        )
    )

internal fun ScheduleUi.toScheduleInfo(): ScheduleInfo = ScheduleInfo(
    id = id,
    name = name,
    createdAtInMillis = createdAtInMillis,
    updatedAtInMillis = updatedAtInMillis,
    isSaturdayWorkingDay = isSaturdayWorkingDay,
    isNumeratorDenominatorSystem = isNumeratorDenominatorSystem,
    isCurrent = isCurrent
)
