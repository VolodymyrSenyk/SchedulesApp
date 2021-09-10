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
import com.senyk.volodymyr.schedulesapp.domain.entity.DayDto;
import com.senyk.volodymyr.schedulesapp.domain.entity.PairDto;
import com.senyk.volodymyr.schedulesapp.domain.entity.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekDto;
import com.senyk.volodymyr.schedulesapp.domain.repository.SchedulesRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RoomSchedulesRepository implements SchedulesRepository {
    private SchedulesManagementDao schedulesManagementDao;
    private PairsManagementDao pairsManagementDao;

    private ScheduleEntityDtoMapper scheduleMapper;
    private DayEntityDtoMapper dayMapper;
    private GenericEntityDtoListMapper<ScheduleDataEntity, ScheduleDto> allSchedulesListMapper;
    private GenericEntityDtoListMapper<WeekDataEntity, WeekDto> fullScheduleMapper;
    private GenericEntityDtoListMapper<PairDataEntity, PairDto> oneDaySchedulePairsMapper;

    public RoomSchedulesRepository(
            SchedulesAppDatabase database,
            ScheduleEntityDtoMapper scheduleMapper,
            DayEntityDtoMapper dayMapper,
            GenericEntityDtoListMapper<ScheduleDataEntity, ScheduleDto> allSchedulesListMapper,
            GenericEntityDtoListMapper<WeekDataEntity, WeekDto> wholeScheduleMapper,
            GenericEntityDtoListMapper<PairDataEntity, PairDto> oneDaySchedulePairsMapper
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
    public Completable createNewSchedule(ScheduleDto schedule) {
        return Completable.fromCallable(() ->
                schedulesManagementDao.createNewSchedule(scheduleMapper.convertToEntity(schedule)));
    }

    @Override
    public Single<List<ScheduleDto>> getSchedulesList() {
        return schedulesManagementDao.getSchedulesList()
                .map(entity -> allSchedulesListMapper.convertToDtos(entity));
    }

    @Override
    public Completable deleteSchedule(final String scheduleName) {
        return Completable.fromCallable(() ->
                schedulesManagementDao.deleteSchedule(scheduleName));
    }

    @Override
    public Completable updateSchedule(final String scheduleName, final int weekNumber, final DayDto day) {
        DayEntity newSchedule = new DayEntity();
        newSchedule.day = dayMapper.convertToEntity(day);
        newSchedule.pairs = oneDaySchedulePairsMapper.convertToEntities(day.getPairs());
        return Completable.fromCallable(() ->
                pairsManagementDao.updateSchedule(scheduleName, weekNumber, day.getOrdinalNumber(), newSchedule));
    }

    @Override
    public Single<DayDto> getScheduleForOneDay(final String scheduleName, final int weekNumber, final int dayNumber) {
        return pairsManagementDao.getSchedule(scheduleName, weekNumber, dayNumber)
                .map(entity -> new DayDto(
                        dayMapper.convertToDto(entity.day).getOrdinalNumber(),
                        oneDaySchedulePairsMapper.convertToDtos(entity.pairs)));
    }

    @Override
    public Single<List<WeekDto>> getFullSchedule(final String scheduleName) {
        return pairsManagementDao.getScheduleByName(scheduleName)
                .map(entity -> fullScheduleMapper.convertToDtos(entity.weeks));
    }
}
