package com.senyk.volodymyr.schedulesapp.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.senyk.volodymyr.schedulesapp.data.datasource.database.AppRoomDatabase
import com.senyk.volodymyr.schedulesapp.data.datasource.database.converter.BooleanTypeConverter
import com.senyk.volodymyr.schedulesapp.data.datasource.database.converter.DayTypeConverter
import com.senyk.volodymyr.schedulesapp.data.datasource.database.converter.PairTypeConverter
import com.senyk.volodymyr.schedulesapp.data.datasource.database.converter.WeekTypeConverter
import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.ScheduleDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAppRoomDatabase(context: Context): AppRoomDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppRoomDatabase::class.java,
        DATABASE_NAME
    )
        .addTypeConverter(BooleanTypeConverter())
        .addTypeConverter(WeekTypeConverter())
        .addTypeConverter(DayTypeConverter())
        .addTypeConverter(PairTypeConverter())
        .build()

    @Provides
    fun provideScheduleDao(database: AppRoomDatabase): ScheduleDao = database.getScheduleDao()

    companion object {
        private const val SHARED_PREFS_NAME = "AppSharedPrefs"
        private const val DATABASE_NAME = "AppDatabase.db"
    }
}
