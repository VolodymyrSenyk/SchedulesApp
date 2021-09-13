package com.senyk.volodymyr.schedulesapp.presentation.feature

import com.senyk.volodymyr.schedulesapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().context(applicationContext).build()
}
