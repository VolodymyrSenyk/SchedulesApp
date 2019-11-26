package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ResourcesProviderModule;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.PairDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ResourcesProviderModule.class)
public class DtoUiSimpleMappersModule {
    @Singleton
    @Provides
    public ScheduleDtoUiMapper getScheduleMapper(ResourcesProvider resourcesProvider) {
        return new ScheduleDtoUiMapper(resourcesProvider);
    }

    @Singleton
    @Provides
    public PairDtoUiMapper getPairMapper(ResourcesProvider resourcesProvider) {
        return new PairDtoUiMapper(resourcesProvider);
    }
}
