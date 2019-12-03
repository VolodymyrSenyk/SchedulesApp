package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ResourcesProviderModule;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.PairsMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.SchedulesMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.PairDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ResourcesProviderModule.class)
public class DtoUiSimpleMappersModule {
    @Singleton
    @Provides
    ScheduleDtoUiMapper getScheduleMapper(SchedulesMappingResourcesProvider resourcesProvider) {
        return new ScheduleDtoUiMapper(resourcesProvider);
    }

    @Singleton
    @Provides
    PairDtoUiMapper getPairMapper(PairsMappingResourcesProvider resourcesProvider) {
        return new PairDtoUiMapper(resourcesProvider);
    }
}
