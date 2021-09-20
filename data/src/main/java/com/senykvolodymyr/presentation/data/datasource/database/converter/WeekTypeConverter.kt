package com.senykvolodymyr.presentation.data.datasource.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.senykvolodymyr.presentation.domain.entity.WeekType

@ProvidedTypeConverter
class WeekTypeConverter {

    @TypeConverter
    fun toWeekType(value: Int) = enumValues<WeekType>()[value]

    @TypeConverter
    fun fromWeekType(value: WeekType) = value.ordinal
}
