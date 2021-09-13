package com.senyk.volodymyr.schedulesapp.di.module

import com.senyk.volodymyr.schedulesapp.di.module.main.SchedulesAppActivityFragmentsContributor
import com.senyk.volodymyr.schedulesapp.di.annotation.scope.ActivityScope
import com.senyk.volodymyr.schedulesapp.di.module.main.SchedulesAppActivityModule
import com.senyk.volodymyr.schedulesapp.presentation.feature.SchedulesAppActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
interface ActivitiesContributor {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            SchedulesAppActivityModule::class,
            SchedulesAppActivityFragmentsContributor::class
        ]
    )
    fun contributeSchedulesAppActivity(): SchedulesAppActivity
}
