package com.senyk.volodymyr.schedulesapp.data.datasource.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class BooleanTypeConverter {

    @TypeConverter
    fun toBoolean(value: Int) = value == 1

    @TypeConverter
    fun fromBoolean(value: Boolean) = if (value) 1 else 0
}
