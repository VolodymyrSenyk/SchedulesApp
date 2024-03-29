package com.senyk.volodymyr.schedulesapp.di.module.mappers.entitydto;

import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.DayEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.PairEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.ScheduleEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.WeekEntityDtoMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityDtoSimpleMappersModule {
    @Singleton
    @Provides
    ScheduleEntityDtoMapper getScheduleMapper() {
        return new ScheduleEntityDtoMapper();
    }

    @Singleton
    @Provides
    WeekEntityDtoMapper getWeekMapper() {
        return new WeekEntityDtoMapper();
    }

    @Singleton
    @Provides
    DayEntityDtoMapper getDayMapper() {
        return new DayEntityDtoMapper();
    }

    @Singleton
    @Provides
    PairEntityDtoMapper getPairMapper() {
        return new PairEntityDtoMapper();
    }
}
