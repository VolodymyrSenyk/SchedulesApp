package com.senyk.volodymyr.schedulesapp.di.module.mappers.uilistitem;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ResourcesProviderModule;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistitemmappers.PairListItemMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ResourcesProviderModule.class)
public class UiListItemSimpleMappersModule {
    @Singleton
    @Provides
    public PairListItemMapper getPairListItemMapper(ResourcesProvider resourcesProvider) {
        return new PairListItemMapper(resourcesProvider);
    }
}
