package com.senykvolodymyr.presentation.data.datasource.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.senykvolodymyr.presentation.domain.entity.PairType

@ProvidedTypeConverter
class PairTypeConverter {

    @TypeConverter
    fun toPairType(value: Int) = enumValues<PairType>()[value]

    @TypeConverter
    fun fromPairType(value: PairType) = value.ordinal
}
