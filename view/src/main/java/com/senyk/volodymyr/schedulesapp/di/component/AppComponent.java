package com.senyk.volodymyr.schedulesapp.di.component;

import com.senyk.volodymyr.schedulesapp.di.module.ViewModelsModule;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ViewModelsModule.class)
public interface AppComponent {
    void inject(BaseFragment fragment);
}
