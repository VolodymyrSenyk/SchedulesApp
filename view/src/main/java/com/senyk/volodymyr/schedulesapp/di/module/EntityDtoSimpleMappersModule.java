package com.senyk.volodymyr.schedulesapp.di.module;

import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.DayMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.PairMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.ScheduleMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.WeekMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityDtoSimpleMappersModule {
    @Singleton
    @Provides
    public ScheduleMapper getScheduleMapper() {
        return new ScheduleMapper();
    }

    @Singleton
    @Provides
    public WeekMapper getWeekMapper() {
        return new WeekMapper();
    }

    @Singleton
    @Provides
    public DayMapper getDayMapper() {
        return new DayMapper();
    }

    @Singleton
    @Provides
    public PairMapper getPairMapper() {
        return new PairMapper();
    }
}
