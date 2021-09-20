package com.senykvolodymyr.presentation.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senykvolodymyr.presentation.data.datasource.database.converter.BooleanTypeConverter
import com.senykvolodymyr.presentation.data.datasource.database.converter.DayTypeConverter
import com.senykvolodymyr.presentation.data.datasource.database.converter.PairTypeConverter
import com.senykvolodymyr.presentation.data.datasource.database.converter.WeekTypeConverter
import com.senykvolodymyr.presentation.data.datasource.database.dao.PairDao
import com.senykvolodymyr.presentation.data.datasource.database.dao.ScheduleDao
import com.senykvolodymyr.presentation.data.datasource.database.entity.PairDbo
import com.senykvolodymyr.presentation.data.datasource.database.entity.ScheduleDbo

@Database(
    entities = [ScheduleDbo::class, PairDbo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BooleanTypeConverter::class,
    WeekTypeConverter::class,
    DayTypeConverter::class,
    PairTypeConverter::class
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getScheduleDao(): ScheduleDao

    abstract fun getPairDao(): PairDao
}
