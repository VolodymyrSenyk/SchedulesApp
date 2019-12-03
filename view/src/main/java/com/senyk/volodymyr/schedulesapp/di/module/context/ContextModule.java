package com.senyk.volodymyr.schedulesapp.di.module.context;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context getContext() {
        return this.context.getApplicationContext();
    }

}
