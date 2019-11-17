package com.senyk.volodymyr.schedulesapp.di.module;

import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.schedules.RoomSchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.usersettings.SharedPreferencesUserSettingsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module()
public class RepositoriesModule {
    @Singleton
    @Provides
    public SchedulesRepository getSchedulesRepository() {
        return new RoomSchedulesRepository();
    }

    @Singleton
    @Provides
    public UserSettingsRepository getUserSettingsRepository() {
        return new SharedPreferencesUserSettingsRepository();
    }

}
