package com.senyk.volodymyr.schedulesapp.data.datasource.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType

@ProvidedTypeConverter
class WeekTypeConverter {

    @TypeConverter
    fun toWeekType(value: Int) = enumValues<WeekType>()[value]

    @TypeConverter
    fun fromWeekType(value: WeekType) = value.ordinal
}
