package com.senyk.volodymyr.schedulesapp.di.module.mappers.entitydto;

import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.PairEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.ScheduleEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.WeekEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtolist.GenericEntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.WeekDto;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = EntityDtoSimpleMappersModule.class)
public class EntityDtoListsMappersModule {
    @Singleton
    @Provides
    public GenericEntityDtoListMapper<ScheduleDataEntity, ScheduleDto> getAllSchedulesListMapper(ScheduleEntityDtoMapper scheduleMapper) {
        return new GenericEntityDtoListMapper<>(scheduleMapper);
    }

    @Singleton
    @Provides
    public GenericEntityDtoListMapper<WeekDataEntity, WeekDto> getWholeScheduleMapper(WeekEntityDtoMapper weekMapper) {
        return new GenericEntityDtoListMapper<>(weekMapper);
    }

    @Singleton
    @Provides
    public GenericEntityDtoListMapper<PairDataEntity, PairDto> getPairsListMapper(PairEntityDtoMapper pairMapper) {
        return new GenericEntityDtoListMapper<>(pairMapper);
    }
}
