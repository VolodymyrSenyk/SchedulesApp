package com.senykvolodymyr.presentation.di.module.main

import com.senykvolodymyr.core.di.annotation.scope.FragmentScope
import com.senykvolodymyr.presentation.feature.schedules.NumeratorDenominatorScheduleFragment
import com.senykvolodymyr.presentation.feature.schedules.OneDayScheduleFragment
import com.senykvolodymyr.presentation.feature.schedules.StudyWeekFragment
import com.senykvolodymyr.presentation.feature.schedulesmanager.SchedulesManagerFragment
import com.senykvolodymyr.presentation.feature.wholeschedule.WholeScheduleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SchedulesAppActivityFragmentsContributor {

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeSchedulesManagerFragment(): SchedulesManagerFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeNumeratorDenominatorScheduleFragment(): NumeratorDenominatorScheduleFragment

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
