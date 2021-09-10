package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.domain.entity.PairDto;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtoui.DayDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.view.mapper.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.view.entity.PairUi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DtoUiListsMappersModule.class)
public class DtoUiComplicatedModelsMappersModule {
    @Singleton
    @Provides
    DayDtoUiMapper getOneDayScheduleMapper(GenericDtoUiListMapper<PairDto, PairUi> pairsMapper) {
        return new DayDtoUiMapper(pairsMapper);
    }
}
