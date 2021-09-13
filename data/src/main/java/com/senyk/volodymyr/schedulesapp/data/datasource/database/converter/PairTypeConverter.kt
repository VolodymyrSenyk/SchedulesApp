package com.senyk.volodymyr.schedulesapp.data.datasource.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.senyk.volodymyr.schedulesapp.domain.entity.PairType

@ProvidedTypeConverter
class PairTypeConverter {

    @TypeConverter
    fun toPairType(value: Int) = enumValues<PairType>()[value]

    @TypeConverter
    fun fromPairType(value: PairType) = value.ordinal
}
