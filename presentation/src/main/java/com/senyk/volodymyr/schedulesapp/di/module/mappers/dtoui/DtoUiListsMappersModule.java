package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.domain.entity.PairDto;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtoui.PairDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist.SchedulesDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.PairUi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DtoUiSimpleMappersModule.class)
public class DtoUiListsMappersModule {
    @Singleton
    @Provides
    SchedulesDtoUiListMapper getSchedulesListMapper(ScheduleDtoUiMapper scheduleMapper) {
        return new SchedulesDtoUiListMapper(scheduleMapper);
    }

    @Singleton
    @Provides
    GenericDtoUiListMapper<PairDto, PairUi> getPairsListMapper(PairDtoUiMapper pairMapper) {
        return new GenericDtoUiListMapper<>(pairMapper);
    }
}
