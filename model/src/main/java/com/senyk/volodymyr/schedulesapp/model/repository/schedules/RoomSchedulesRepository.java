package com.senyk.volodymyr.schedulesapp.model.repository.schedules;

import com.senyk.volodymyr.schedulesapp.model.database.SchedulesAppDatabase;
import com.senyk.volodymyr.schedulesapp.model.database.dao.PairsManagementDao;
import com.senyk.volodymyr.schedulesapp.model.database.dao.SchedulesManagementDao;
import com.senyk.volodymyr.schedulesapp.model.mappers.base.EntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.DayMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.ScheduleMapper;
import com.senyk.volodymyr.schedulesapp.model.models.dto.DayDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.WeekDto;
import com.senyk.volodymyr.schedulesapp.model.models.entities.DayEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RoomSchedulesRepository implements SchedulesRepository {
    private SchedulesManagementDao schedulesManagementDao;
    private PairsManagementDao pairsManagementDao;

    private ScheduleMapper scheduleMapper;
    private DayMapper dayMapper;
    private EntityDtoListMapper<ScheduleDataEntity, ScheduleDto> allSchedulesListMapper;
    private EntityDtoListMapper<WeekDataEntity, WeekDto> allScheduleMapper;
    private EntityDtoListMapper<PairDataEntity, PairDto> oneDayScheduleMapper;

    public RoomSchedulesRepository(
            SchedulesAppDatabase database,
            ScheduleMapper scheduleMapper,
            DayMapper dayMapper,
            EntityDtoListMapper<ScheduleDataEntity, ScheduleDto> allSchedulesListMapper,
            EntityDtoListMapper<WeekDataEntity, WeekDto> allScheduleMapper,
            EntityDtoListMapper<PairDataEntity, PairDto> oneDayScheduleMapper
    ) {
        this.schedulesManagementDao = database.getSchedulesManagementDao();
        this.pairsManagementDao = database.getPairsManagementDao();

        this.scheduleMapper = scheduleMapper;
        this.dayMapper = dayMapper;

        this.allSchedulesListMapper = allSchedulesListMapper;
        this.allScheduleMapper = allScheduleMapper;
        this.oneDayScheduleMapper = oneDayScheduleMapper;
    }

    @Override
    public Completable createNewSchedule(ScheduleDto schedule) {
        return schedulesManagementDao.createNewSchedule(scheduleMapper.convertToEntity(schedule));
    }

    @Override
    public Single<List<ScheduleDto>> getSchedulesList() {
        return schedulesManagementDao.getSchedulesList()
                .map(entity -> allSchedulesListMapper.convertToDtos(entity));
    }

    @Override
    public Completable deleteSchedule(final String scheduleName) {
        return schedulesManagementDao.deleteSchedule(scheduleName);
    }

    @Override
    public Completable updateSchedule(final String scheduleName, final int weekNumber, final DayDto day) {
        DayEntity newSchedule = new DayEntity();
        newSchedule.day = dayMapper.convertToEntity(day);
        newSchedule.pairs = oneDayScheduleMapper.convertToEntities(day.getPairs());
        return pairsManagementDao.updateSchedule(scheduleName, weekNumber, day.getOrdinalNumber(), newSchedule);
    }

    @Override
    public Single<List<PairDto>> getScheduleForOneDay(final String scheduleName, final int weekNumber, final int dayNumber) {
        return pairsManagementDao.getSchedule(scheduleName, weekNumber, dayNumber)
                .map(entity -> oneDayScheduleMapper.convertToDtos(entity.pairs));
    }

    @Override
    public Single<List<WeekDto>> getFullSchedule(final String scheduleName) {
        return pairsManagementDao.getScheduleByName(scheduleName)
                .map(entity -> allScheduleMapper.convertToDtos(entity.weeks));
    }
}
