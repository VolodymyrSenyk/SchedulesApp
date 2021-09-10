package com.senyk.volodymyr.schedulesapp.di.module.repository;

import android.content.SharedPreferences;

import com.senyk.volodymyr.schedulesapp.di.module.mappers.entitydto.EntityDtoListsMappersModule;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.SchedulesAppDatabase;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.DayEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydto.ScheduleEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.data.mapper.entitydtolist.GenericEntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair;
import com.senyk.volodymyr.schedulesapp.domain.entity.Schedule;
import com.senyk.volodymyr.schedulesapp.domain.entity.Week;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata.WeekDataEntity;
import com.senyk.volodymyr.schedulesapp.domain.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.domain.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.data.repository.schedules.RoomSchedulesRepository;
import com.senyk.volodymyr.schedulesapp.data.repository.usersettings.SharedPreferencesUserSettingsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        SharedPreferencesModule.class,
        DatabasesModule.class,
        EntityDtoListsMappersModule.class
})
public class RepositoriesModule {
    @Singleton
    @Provides
    SchedulesRepository getSchedulesRepository(
            SchedulesAppDatabase database,
            ScheduleEntityDtoMapper scheduleMapper,
            DayEntityDtoMapper dayMapper,
            GenericEntityDtoListMapper<ScheduleDataEntity, Schedule> allSchedulesListMapper,
            GenericEntityDtoListMapper<WeekDataEntity, Week> allScheduleMapper,
            GenericEntityDtoListMapper<PairDataEntity, Pair> oneDayScheduleMapper) {
        return new RoomSchedulesRepository(
                database,
                scheduleMapper,
                dayMapper,
                allSchedulesListMapper,
                allScheduleMapper,
                oneDayScheduleMapper);
    }

    @Singleton
    @Provides
    UserSettingsRepository getUserSettingsRepository(SharedPreferences sharedPreferences) {
        return new SharedPreferencesUserSettingsRepository(sharedPreferences);
    }
}
