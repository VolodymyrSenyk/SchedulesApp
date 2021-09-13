package com.senyk.volodymyr.schedulesapp.di.module.main

import com.senyk.volodymyr.schedulesapp.presentation.core.base.activity.BaseActivity
import com.senyk.volodymyr.schedulesapp.di.annotation.scope.ActivityScope
import com.senyk.volodymyr.schedulesapp.presentation.feature.SchedulesAppActivity
import dagger.Binds
import dagger.Module

@Module
interface SchedulesAppActivityModule {

    @ActivityScope
    @Binds
    fun bindActivity(activity: SchedulesAppActivity): BaseActivity
}
