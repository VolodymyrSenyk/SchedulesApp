package com.senyk.volodymyr.schedulesapp.di.module.helpers;

import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.ApplicationResourcesProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ResourcesProviderModule.class)
public class ErrorsHandlerModule {
    @Singleton
    @Provides
    ErrorsHandler getErrorsHandler(ApplicationResourcesProvider resourcesProvider) {
        return new ErrorsHandler(resourcesProvider);
    }
}
