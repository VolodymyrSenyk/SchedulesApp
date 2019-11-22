package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ResourcesProviderModule;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouimappers.PairMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouimappers.ScheduleMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ResourcesProviderModule.class)
public class DtoUiSimpleMappersModule {
    @Singleton
    @Provides
    public ScheduleMapper getScheduleMapper(ResourcesProvider resourcesProvider) {
        return new ScheduleMapper(resourcesProvider);
    }

    @Singleton
    @Provides
    public PairMapper getPairMapper(ResourcesProvider resourcesProvider) {
        return new PairMapper(resourcesProvider);
    }
}
