package com.senyk.volodymyr.schedulesapp.di.module.mappers.entitydto;

import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.EntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.PairMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.ScheduleMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.WeekMapper;
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
    public EntityDtoListMapper<ScheduleDataEntity, ScheduleDto> getAllSchedulesListMapper(ScheduleMapper scheduleMapper) {
        return new EntityDtoListMapper<>(scheduleMapper);
    }

    @Singleton
    @Provides
    public EntityDtoListMapper<WeekDataEntity, WeekDto> getAllScheduleMapper(WeekMapper weekMapper) {
        return new EntityDtoListMapper<>(weekMapper);
    }

    @Singleton
    @Provides
    public EntityDtoListMapper<PairDataEntity, PairDto> getOneDayScheduleMapper(PairMapper pairMapper) {
        return new EntityDtoListMapper<>(pairMapper);
    }
}
