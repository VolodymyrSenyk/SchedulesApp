package com.senyk.volodymyr.schedulesapp.di.module;

import android.content.SharedPreferences;

import com.senyk.volodymyr.schedulesapp.model.database.SchedulesAppDatabase;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.DayMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.EntityDtoListMapper;
import com.senyk.volodymyr.schedulesapp.model.mappers.entitydtomappers.ScheduleMapper;
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
        EntityDtoListsMappersModule.class,
        EntityDtoListsMappersModule.class
})
public class RepositoriesModule {
    @Singleton
    @Provides
    public SchedulesRepository getSchedulesRepository(
            SchedulesAppDatabase database,
            ScheduleMapper scheduleMapper,
            DayMapper dayMapper,
            EntityDtoListMapper<ScheduleDataEntity, ScheduleDto> allSchedulesListMapper,
            EntityDtoListMapper<WeekDataEntity, WeekDto> allScheduleMapper,
            EntityDtoListMapper<PairDataEntity, PairDto> oneDayScheduleMapper
    ) {
        return new RoomSchedulesRepository(
                database,
                scheduleMapper,
                dayMapper,
                allSchedulesListMapper,
                allScheduleMapper,
                oneDayScheduleMapper
        );
    }

    @Singleton
    @Provides
    public UserSettingsRepository getUserSettingsRepository(SharedPreferences sharedPreferences) {
        return new SharedPreferencesUserSettingsRepository(sharedPreferences);
    }

}
