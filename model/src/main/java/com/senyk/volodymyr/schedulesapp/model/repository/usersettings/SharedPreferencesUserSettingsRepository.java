package com.senyk.volodymyr.schedulesapp.model.repository.usersettings;

import android.content.SharedPreferences;

import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;

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
        return Single.fromCallable(() -> sharedPreferences.getString(
                SchedulesAppSharedPreferencesContract.CURRENT_SCHEDULE_NAME_KEY, ""
        ));
    }

    @Override
    public Completable setSchedule(final String scheduleName) {
        return Completable.fromCallable((Callable<Boolean>) () -> {
            sharedPreferences
                    .edit()
                    .putString(
                            SchedulesAppSharedPreferencesContract.CURRENT_SCHEDULE_NAME_KEY,
                            scheduleName
                    )
                    .apply();
            return !sharedPreferences.getString(
                    SchedulesAppSharedPreferencesContract.CURRENT_SCHEDULE_NAME_KEY, ""
            ).equals("");
        });
    }
}
