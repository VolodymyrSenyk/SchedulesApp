package com.senykvolodymyr.presentation.data.datasource.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.senykvolodymyr.presentation.domain.entity.DayType

@ProvidedTypeConverter
class DayTypeConverter {

    @TypeConverter
    fun toDayType(value: Int) = enumValues<DayType>()[value]

    @TypeConverter
    fun fromDayType(value: DayType) = value.ordinal
}
