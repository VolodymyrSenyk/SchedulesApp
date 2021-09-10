package com.senyk.volodymyr.schedulesapp.data.repository.usersettings;

import android.content.SharedPreferences;

import com.senyk.volodymyr.schedulesapp.domain.repository.UserSettingsRepository;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SharedPreferencesUserSettingsRepository implements UserSettingsRepository {
    private SharedPreferences sharedPreferences;

    public SharedPreferencesUserSettingsRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Single<String> getCurrentSchedule() {
        return Single.fromCallable(() -> this.sharedPreferences.getString(
                SchedulesAppSharedPreferencesContract.CURRENT_SCHEDULE_NAME_KEY, ""
        ));
    }

    @Override
    public Completable setCurrentSchedule(final String scheduleName) {
        return Completable.fromCallable((Callable<Boolean>) () -> {
            this.sharedPreferences
                    .edit()
                    .putString(
                            SchedulesAppSharedPreferencesContract.CURRENT_SCHEDULE_NAME_KEY, scheduleName)
                    .apply();
            return !this.sharedPreferences.getString(
                    SchedulesAppSharedPreferencesContract.CURRENT_SCHEDULE_NAME_KEY, "")
                    .equals("");
        });
    }
}
