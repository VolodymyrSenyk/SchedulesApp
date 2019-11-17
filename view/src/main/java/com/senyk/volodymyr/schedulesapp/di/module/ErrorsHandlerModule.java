package com.senyk.volodymyr.schedulesapp.di.module;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ResourcesProviderModule.class)
public class ErrorsHandlerModule {
    @Singleton
    @Provides
    public ErrorsHandler getErrorsHandler(ResourcesProvider resourcesProvider) {
        return new ErrorsHandler(resourcesProvider);
    }

}
