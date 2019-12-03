package com.senyk.volodymyr.schedulesapp.di.module.helpers;

import android.content.Context;

import com.senyk.volodymyr.schedulesapp.di.module.context.ContextModule;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.ApplicationResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.PairsMappingResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.resourcesproviders.SchedulesMappingResourcesProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class ResourcesProviderModule {
    @Singleton
    @Provides
    ApplicationResourcesProvider getApplicationResourcesProvider(Context context) {
        return new ApplicationResourcesProvider(context);
    }

    @Singleton
    @Provides
    PairsMappingResourcesProvider getPairsMappingResourcesProvider(Context context) {
        return new PairsMappingResourcesProvider(context);
    }

    @Singleton
    @Provides
    SchedulesMappingResourcesProvider getSchedulesMappingResourcesProvider(Context context) {
        return new SchedulesMappingResourcesProvider(context);
    }
}
