package com.senyk.volodymyr.schedulesapp.di.module.main

import com.senyk.volodymyr.schedulesapp.di.annotation.scope.FragmentScope
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedules.NumDenomScheduleFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedules.OneDayScheduleFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedules.StudyWeekFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.wholeschedule.WholeScheduleFragment
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedulesmanager.SchedulesManagerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SchedulesAppActivityFragmentsContributor {

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeSchedulesManagerFragment(): SchedulesManagerFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeNumDenomScheduleFragment(): NumDenomScheduleFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeStudyWeekFragment(): StudyWeekFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeOneDayScheduleFragment(): OneDayScheduleFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeWholeScheduleFragment(): WholeScheduleFragment
}
