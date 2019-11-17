package com.senyk.volodymyr.schedulesapp.di.module;

import android.content.Context;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class ResourcesProviderModule {
    @Singleton
    @Provides
    public ResourcesProvider getResourcesProvider(Context context) {
        return new ResourcesProvider(context.getResources());
    }

}
