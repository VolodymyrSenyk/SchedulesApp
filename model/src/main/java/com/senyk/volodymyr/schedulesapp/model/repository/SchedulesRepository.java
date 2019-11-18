package com.senyk.volodymyr.schedulesapp.model.repository;

import com.senyk.volodymyr.schedulesapp.model.models.dto.DayDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.WeekDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface SchedulesRepository {
    Completable createNewSchedule(ScheduleDto schedule);

    Single<List<ScheduleDto>> getSchedulesList();

    Completable deleteSchedule(String scheduleName);

    Completable updateSchedule(String scheduleName, int weekNumber, DayDto day);

    Single<List<PairDto>> getScheduleForOneDay(String scheduleName, int weekNumber, int dayNumber);

    Single<List<WeekDto>> getFullSchedule(String scheduleName);
}
