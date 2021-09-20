package com.senykvolodymyr.core.di.module

import com.senykvolodymyr.core.di.annotation.qualifier.execution.Background
import com.senykvolodymyr.core.di.annotation.qualifier.execution.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulerModule {

    @Background
    @Provides
    fun provideBackgroundScheduler(): Scheduler = Schedulers.computation()

    @Foreground
    @Provides
    fun provideForegroundScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
