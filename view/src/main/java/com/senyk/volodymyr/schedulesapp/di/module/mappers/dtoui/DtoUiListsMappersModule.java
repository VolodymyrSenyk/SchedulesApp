package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.models.dto.ScheduleDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouimappers.DtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouimappers.PairMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouimappers.ScheduleMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DtoUiSimpleMappersModule.class)
public class DtoUiListsMappersModule {
    @Singleton
    @Provides
    public DtoUiListMapper<ScheduleDto, ScheduleUi> getAllSchedulesListMapper(ScheduleMapper scheduleMapper) {
        return new DtoUiListMapper<>(scheduleMapper);
    }

    @Singleton
    @Provides
    public DtoUiListMapper<PairDto, PairUi> getOneDayScheduleMapper(PairMapper pairMapper) {
        return new DtoUiListMapper<>(pairMapper);
    }
}
