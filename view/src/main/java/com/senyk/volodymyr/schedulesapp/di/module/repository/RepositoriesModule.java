package com.senyk.volodymyr.schedulesapp.di.module.repository;

import android.content.SharedPreferences;

import com.senyk.volodymyr.schedulesapp.di.module.mappers.entitydto.EntityDtoListsMappersModule;
import com.senyk.volodymyr.schedulesapp.model.database.SchedulesAppDatabase;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.DayEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydto.ScheduleEntityDtoMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtolist.GenericEntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.WeekDto;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.PairDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.ScheduleDataEntity;
import com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata.WeekDataEntity;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.schedules.RoomSchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.usersettings.SharedPreferencesUserSettingsRepository;

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
            GenericEntityDtoListMapper<ScheduleDataEntity, ScheduleDto> allSchedulesListMapper,
            GenericEntityDtoListMapper<WeekDataEntity, WeekDto> allScheduleMapper,
            GenericEntityDtoListMapper<PairDataEntity, PairDto> oneDayScheduleMapper) {
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
