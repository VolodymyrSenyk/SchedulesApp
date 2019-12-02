package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.DayDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DtoUiListsMappersModule.class)
public class DtoUiComplicatedModelsMappersModule {
    @Singleton
    @Provides
    public DayDtoUiMapper getOneDayScheduleMapper(GenericDtoUiListMapper<PairDto, PairUi> pairsMapper) {
        return new DayDtoUiMapper(pairsMapper);
    }
}
