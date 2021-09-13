package com.senyk.volodymyr.schedulesapp.di.module

import androidx.lifecycle.ViewModel
import com.senyk.volodymyr.presentation.di.annotation.mapkey.ViewModelKey
import com.senyk.volodymyr.schedulesapp.presentation.core.viewmodel.ExitViewModel
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedules.NumeratorDenominatorScheduleViewModel
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedules.OneDayScheduleViewModel
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedules.StudyWeekViewModel
import com.senyk.volodymyr.schedulesapp.presentation.feature.schedulesmanager.SchedulesManagerViewModel
import com.senyk.volodymyr.schedulesapp.presentation.feature.wholeschedule.WholeScheduleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [SchedulerModule::class, RepositoryModule::class])
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExitViewModel::class)
    fun bindExitViewModel(viewModel: ExitViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SchedulesManagerViewModel::class)
    fun bindSchedulesManagerViewModel(viewModel: SchedulesManagerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StudyWeekViewModel::class)
    fun bindStudyWeekViewModel(viewModel: StudyWeekViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NumeratorDenominatorScheduleViewModel::class)
    fun bindNumeratorDenominatorScheduleViewModel(viewModel: NumeratorDenominatorScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OneDayScheduleViewModel::class)
    fun bindOneDayScheduleViewModel(viewModel: OneDayScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WholeScheduleViewModel::class)
    fun bindWholeScheduleViewModel(viewModel: WholeScheduleViewModel): ViewModel
}
