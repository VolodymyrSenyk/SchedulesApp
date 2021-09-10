package com.senyk.volodymyr.schedulesapp.domain.repository;

import com.senyk.volodymyr.schedulesapp.domain.entity.DayDto;
import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface SchedulesRepository {
    Completable createNewSchedule(ScheduleDto schedule);

    Single<List<ScheduleDto>> getSchedulesList();

    Completable deleteSchedule(String scheduleName);

    Completable updateSchedule(String scheduleName, int weekNumber, DayDto day);

    Single<DayDto> getScheduleForOneDay(String scheduleName, int weekNumber, int dayNumber);

    Single<List<WeekDto>> getFullSchedule(String scheduleName);
}
