package com.senyk.volodymyr.schedulesapp.di.module.mappers.uilistitem;

import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistitemmappers.PairListItemMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistitemmappers.UiListItemListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.listsitems.models.pairs.creation.PairCreationItem;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = UiListItemSimpleMappersModule.class)
public class UiListItemListsMappersModule {
    @Singleton
    @Provides
    public UiListItemListMapper<PairUi, PairCreationItem> getEditOneDayScheduleMapper(PairListItemMapper pairMapper) {
        return new UiListItemListMapper<>(pairMapper);
    }
}
