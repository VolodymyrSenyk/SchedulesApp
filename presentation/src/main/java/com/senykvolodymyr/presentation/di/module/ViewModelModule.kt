package com.senykvolodymyr.presentation.di.module

import androidx.lifecycle.ViewModel
import com.senykvolodymyr.core.di.annotation.mapkey.ViewModelKey
import com.senykvolodymyr.core.di.module.SchedulerModule
import com.senykvolodymyr.core.viewmodel.ExitViewModel
import com.senykvolodymyr.presentation.feature.schedules.NumeratorDenominatorScheduleViewModel
import com.senykvolodymyr.presentation.feature.schedules.OneDayScheduleViewModel
import com.senykvolodymyr.presentation.feature.schedules.StudyWeekViewModel
import com.senykvolodymyr.presentation.feature.schedulesmanager.SchedulesManagerViewModel
import com.senykvolodymyr.presentation.feature.wholeschedule.WholeScheduleViewModel
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
