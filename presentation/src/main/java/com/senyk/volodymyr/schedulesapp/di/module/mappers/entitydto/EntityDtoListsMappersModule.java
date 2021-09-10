package com.senyk.volodymyr.schedulesapp.di.module.mappers.entitydto;

import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.PairEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.ScheduleEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.WeekEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydtolist.GenericEntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair;
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule;
import com.senyk.volodymyr.schedulesapp.domain.entity.Week;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.WeekDataEntity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = EntityDtoSimpleMappersModule.class)
public class EntityDtoListsMappersModule {
    @Singleton
    @Provides
    GenericEntityDtoListMapper<ScheduleDataEntity, Schedule> getAllSchedulesListMapper(ScheduleEntityDtoMapper scheduleMapper) {
        return new GenericEntityDtoListMapper<>(scheduleMapper);
    }

    @Singleton
    @Provides
    GenericEntityDtoListMapper<WeekDataEntity, Week> getWholeScheduleMapper(WeekEntityDtoMapper weekMapper) {
        return new GenericEntityDtoListMapper<>(weekMapper);
    }

    @Singleton
    @Provides
    GenericEntityDtoListMapper<PairDataEntity, Pair> getPairsListMapper(PairEntityDtoMapper pairMapper) {
        return new GenericEntityDtoListMapper<>(pairMapper);
    }
}
