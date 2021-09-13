package com.senyk.volodymyr.schedulesapp.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue

interface UserSettingsRepository {

    @CheckReturnValue
    fun getCurrentScheduleId(): Single<String>

    @CheckReturnValue
    fun setCurrentScheduleId(scheduleId: String): Completable
}
