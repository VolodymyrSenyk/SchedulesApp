package com.senyk.volodymyr.schedulesapp.view;

import android.app.Application;

import com.senyk.volodymyr.schedulesapp.di.component.AppComponent;
import com.senyk.volodymyr.schedulesapp.di.component.DaggerAppComponent;
import com.senyk.volodymyr.schedulesapp.di.module.context.ContextModule;

public class SchedulesApplication extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }
}
