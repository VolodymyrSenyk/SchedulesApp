package com.senyk.volodymyr.schedulesapp.domain.repository;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserSettingsRepository {
    Single<String> getCurrentSchedule();

    Completable setCurrentSchedule(String scheduleName);
}
