package com.senykvolodymyr.presentation.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senykvolodymyr.presentation.domain.entity.Schedule

@Entity(tableName = ScheduleDbo.TABLE_NAME)
internal data class ScheduleDbo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Long = 0,
    @ColumnInfo(name = NAME) val name: String = "",
    @ColumnInfo(name = CREATED_AT_IN_MILLIS) val createdAtInMillis: Long = System.currentTimeMillis(),
    @ColumnInfo(name = UPDATED_AT_IN_MILLIS) val updatedAtInMillis: Long = System.currentTimeMillis(),
    @ColumnInfo(name = IS_SATURDAY_WORKING_DAY) val isSaturdayWorkingDay: Boolean = false,
    @ColumnInfo(name = IS_NUMERATOR_DENOMINATOR_SYSTEM) val isNumeratorDenominatorSystem: Boolean = false,
    @ColumnInfo(name = IS_CURRENT) val isCurrent: Boolean = false
) {

    companion object {
        const val TABLE_NAME = "schedules"
        const val ID = "schedule_id"
        const val NAME = "schedule_name"
        const val CREATED_AT_IN_MILLIS = "schedule_created_at_in_millis"
        const val UPDATED_AT_IN_MILLIS = "schedule_updated_at_in_millis"
        const val IS_SATURDAY_WORKING_DAY = "schedule_is_saturday_working_day"
        const val IS_NUMERATOR_DENOMINATOR_SYSTEM = "schedule_is_numerator_denominator_system"
        const val IS_CURRENT = "schedule_is_current"
    }
}

internal fun ScheduleDbo.toSchedule(): Schedule = Schedule(
    id = id.toString(),
    name = name,
    createdAtInMillis = createdAtInMillis,
    updatedAtInMillis = updatedAtInMillis,
    isSaturdayWorkingDay = isSaturdayWorkingDay,
    isNumeratorDenominatorSystem = isNumeratorDenominatorSystem,
    isCurrent = isCurrent
)

internal fun Schedule.toScheduleDbo(): ScheduleDbo = ScheduleDbo(
    id = id.toLong(),
    name = name,
    createdAtInMillis = createdAtInMillis,
    updatedAtInMillis = updatedAtInMillis,
    isSaturdayWorkingDay = isSaturdayWorkingDay,
    isNumeratorDenominatorSystem = isNumeratorDenominatorSystem,
    isCurrent = isCurrent
)
