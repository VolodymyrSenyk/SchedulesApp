package com.senyk.volodymyr.schedulesapp.di.module.repository;

import android.content.Context;

import androidx.room.Room;

import com.senyk.volodymyr.schedulesapp.di.module.context.ContextModule;
import com.senyk.volodymyr.schedulesapp.model.database.SchedulesAppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DatabasesModule {
    @Singleton
    @Provides
    public SchedulesAppDatabase getAppDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                SchedulesAppDatabase.class,
                SchedulesAppDatabase.SCHEDULES_APP_DATABASE_NAME
        ).build();
    }
}
