package com.senyk.volodymyr.schedulesapp.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.senyk.volodymyr.schedulesapp.model.repository.usersettings.SchedulesAppSharedPreferencesContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class SharedPreferencesModule {
    @Singleton
    @Provides
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(
                SchedulesAppSharedPreferencesContract.FILE_NAME, Context.MODE_PRIVATE
        );
    }

}
