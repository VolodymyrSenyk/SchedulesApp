package com.senyk.volodymyr.schedulesapp.di.module.mappers.uilistupdater;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ResourcesProviderModule;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistupdater.SchedulesUiListUpdater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ResourcesProviderModule.class)
public class UiListUpdatersModule {
    @Singleton
    @Provides
    public SchedulesUiListUpdater getSchedulesUiListUpdater(ResourcesProvider resourcesProvider) {
        return new SchedulesUiListUpdater(resourcesProvider);
    }
}
