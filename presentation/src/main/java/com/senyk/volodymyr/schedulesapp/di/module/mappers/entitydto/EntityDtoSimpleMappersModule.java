package com.senyk.volodymyr.schedulesapp.di.module.mappers.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.DayEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.PairEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.ScheduleEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.WeekEntityDtoMapper;

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
