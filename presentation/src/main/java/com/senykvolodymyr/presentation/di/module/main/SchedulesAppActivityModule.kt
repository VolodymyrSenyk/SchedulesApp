package com.senykvolodymyr.presentation.di.module.main

import com.senykvolodymyr.core.base.activity.BaseActivity
import com.senykvolodymyr.core.di.annotation.scope.ActivityScope
import com.senykvolodymyr.presentation.feature.SchedulesAppActivity
import dagger.Binds
import dagger.Module

@Module
interface SchedulesAppActivityModule {

    @ActivityScope
    @Binds
    fun bindActivity(activity: SchedulesAppActivity): BaseActivity<*>
}
