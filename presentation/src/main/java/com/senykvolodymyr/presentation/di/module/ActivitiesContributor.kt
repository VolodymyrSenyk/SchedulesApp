package com.senykvolodymyr.presentation.di.module

import com.senykvolodymyr.core.di.annotation.scope.ActivityScope
import com.senykvolodymyr.presentation.di.module.main.SchedulesAppActivityFragmentsContributor
import com.senykvolodymyr.presentation.di.module.main.SchedulesAppActivityModule
import com.senykvolodymyr.presentation.feature.SchedulesAppActivity
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
