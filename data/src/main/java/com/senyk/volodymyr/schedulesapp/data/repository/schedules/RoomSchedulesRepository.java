package com.senyk.volodymyr.schedulesapp.data.repository.schedules;

import com.senyk.volodymyr.schedulesapp.data.datasource.database.SchedulesAppDatabase;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.PairsManagementDao;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.SchedulesManagementDao;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.DayEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.WeekDataEntity;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.DayEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.ScheduleEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydtolist.GenericEntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.Day;
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair;
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule;
import com.senyk.volodymyr.schedulesapp.domain.entity.Week;
import com.senyk.volodymyr.schedulesapp.domain.repository.SchedulesRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RoomSchedulesRepository implements SchedulesRepository {
    private SchedulesManagementDao schedulesManagementDao;
    private PairsManagementDao pairsManagementDao;

    private ScheduleEntityDtoMapper scheduleMapper;
    private DayEntityDtoMapper dayMapper;
    private GenericEntityDtoListMapper<ScheduleDataEntity, Schedule> allSchedulesListMapper;
    private GenericEntityDtoListMapper<WeekDataEntity, Week> fullScheduleMapper;
    private GenericEntityDtoListMapper<PairDataEntity, Pair> oneDaySchedulePairsMapper;

    public RoomSchedulesRepository(
            SchedulesAppDatabase database,
            ScheduleEntityDtoMapper scheduleMapper,
            DayEntityDtoMapper dayMapper,
            GenericEntityDtoListMapper<ScheduleDataEntity, Schedule> allSchedulesListMapper,
            GenericEntityDtoListMapper<WeekDataEntity, Week> wholeScheduleMapper,
            GenericEntityDtoListMapper<PairDataEntity, Pair> oneDaySchedulePairsMapper
    ) {
        this.schedulesManagementDao = database.getSchedulesManagementDao();
        this.pairsManagementDao = database.getPairsManagementDao();

        this.scheduleMapper = scheduleMapper;
        this.dayMapper = dayMapper;

        this.allSchedulesListMapper = allSchedulesListMapper;
        this.fullScheduleMapper = wholeScheduleMapper;
        this.oneDaySchedulePairsMapper = oneDaySchedulePairsMapper;
    }

    @Override
    public Completable createNewSchedule(Schedule schedule) {
        return Completable.fromCallable(() ->
                schedulesManagementDao.createNewSchedule(scheduleMapper.convertToEntity(schedule)));
    }

    @Override
    public Single<List<Schedule>> getSchedulesList() {
        return schedulesManagementDao.getSchedulesList()
                .map(entity -> allSchedulesListMapper.convertToDtos(entity));
    }

    @Override
    public Completable deleteSchedule(final String scheduleName) {
        return Completable.fromCallable(() ->
                schedulesManagementDao.deleteSchedule(scheduleName));
    }

    @Override
    public Completable updateSchedule(final String scheduleName, final int weekNumber, final Day day) {
        DayEntity newSchedule = new DayEntity();
        newSchedule.day = dayMapper.convertToEntity(day);
        newSchedule.pairs = oneDaySchedulePairsMapper.convertToEntities(day.getPairs());
        return Completable.fromCallable(() ->
                pairsManagementDao.updateSchedule(scheduleName, weekNumber, day.getOrdinalNumber(), newSchedule));
    }

    @Override
    public Single<Day> getScheduleForOneDay(final String scheduleName, final int weekNumber, final int dayNumber) {
        return pairsManagementDao.getSchedule(scheduleName, weekNumber, dayNumber)
                .map(entity -> new Day(
                        dayMapper.convertToDto(entity.day).getOrdinalNumber(),
                        oneDaySchedulePairsMapper.convertToDtos(entity.pairs)));
    }

    @Override
    public Single<List<Week>> getFullSchedule(final String scheduleName) {
        return pairsManagementDao.getScheduleByName(scheduleName)
                .map(entity -> fullScheduleMapper.convertToDtos(entity.weeks));
    }
}
