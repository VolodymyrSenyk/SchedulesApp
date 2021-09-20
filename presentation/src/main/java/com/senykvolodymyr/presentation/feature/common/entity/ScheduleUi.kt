package com.senykvolodymyr.presentation.feature.common.entity

import androidx.annotation.ColorInt
import com.senykvolodymyr.core.provider.ResourcesProvider
import com.senykvolodymyr.core.recyclerview.listitem.ListItem
import com.senykvolodymyr.core.util.DateFormatterUtil
import com.senykvolodymyr.presentation.R
import com.senykvolodymyr.presentation.domain.entity.Schedule

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

internal fun Schedule.toScheduleUi(resourcesProvider: ResourcesProvider): ScheduleUi =
    ScheduleUi(
        id = id,
        name = name,
        createdAtInMillis = createdAtInMillis,
        updatedAtInMillis = updatedAtInMillis,
        createdAt = resourcesProvider.getString(
            R.string.schedule_date_of_creation_output_pattern,
            DateFormatterUtil.getShortDateAndTime(
                createdAtInMillis, resourcesProvider.getCurrentLocale()
            )
        ),
        updatedAt = resourcesProvider.getString(
            R.string.schedule_date_of_update_output_pattern, DateFormatterUtil.getShortDateAndTime(
                updatedAtInMillis, resourcesProvider.getCurrentLocale()
            )
        ),
        isSaturdayWorkingDay = isSaturdayWorkingDay,
        isNumeratorDenominatorSystem = isNumeratorDenominatorSystem,
        isCurrent = isCurrent,
        tintColorInt = resourcesProvider.getColorInt(
            if (isCurrent) R.color.colorSelected else R.color.colorNotSelected
        )
    )

internal fun ScheduleUi.toScheduleInfo(): Schedule = Schedule(
    id = id,
    name = name,
    createdAtInMillis = createdAtInMillis,
    updatedAtInMillis = updatedAtInMillis,
    isSaturdayWorkingDay = isSaturdayWorkingDay,
    isNumeratorDenominatorSystem = isNumeratorDenominatorSystem,
    isCurrent = isCurrent
)
