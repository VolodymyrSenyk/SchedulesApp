package com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui;

import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.PairDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.SchedulesDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DtoUiSimpleMappersModule.class)
public class DtoUiListsMappersModule {
    @Singleton
    @Provides
    public SchedulesDtoUiListMapper getAllSchedulesListMapper(
            ScheduleDtoUiMapper scheduleMapper,
            ResourcesProvider resourcesProvider
    ) {
        return new SchedulesDtoUiListMapper(scheduleMapper, resourcesProvider);
    }

    @Singleton
    @Provides
    public GenericDtoUiListMapper<PairDto, PairUi> getOneDayScheduleMapper(PairDtoUiMapper pairMapper) {
        return new GenericDtoUiListMapper<>(pairMapper);
    }
}
