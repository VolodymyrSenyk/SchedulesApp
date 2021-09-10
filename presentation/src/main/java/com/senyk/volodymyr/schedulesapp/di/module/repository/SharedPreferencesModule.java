package com.senyk.volodymyr.schedulesapp.di.module.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.senyk.volodymyr.schedulesapp.di.module.context.ContextModule;
import com.senyk.volodymyr.schedulesapp.data.repository.usersettings.SchedulesAppSharedPreferencesContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class SharedPreferencesModule {
    @Singleton
    @Provides
    SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SchedulesAppSharedPreferencesContract.FILE_NAME, Context.MODE_PRIVATE);
    }
}
