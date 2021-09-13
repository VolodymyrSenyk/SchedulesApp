package com.senyk.volodymyr.schedulesapp.data.repository

import com.senyk.volodymyr.schedulesapp.data.datasource.filesystem.SharedPrefsReaderWriter
import com.senyk.volodymyr.schedulesapp.domain.repository.UserSettingsRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserSettingsSharedPreferencesRepository @Inject constructor(
    private val readerWriter: SharedPrefsReaderWriter
) : UserSettingsRepository {

    override fun getCurrentScheduleId(): Single<String> = readerWriter.readString(
        bundleKey = BUNDLE_KEY_CURRENT_SCHEDULE_ID,
        defaultValue = ""
    )

    override fun setCurrentScheduleId(scheduleId: String): Completable = readerWriter.writeString(
        bundleKey = BUNDLE_KEY_CURRENT_SCHEDULE_ID,
        data = scheduleId
    )

    companion object {
        private const val BUNDLE_KEY_CURRENT_SCHEDULE_ID = "BUNDLE_KEY_CURRENT_SCHEDULE_ID"
    }
}
