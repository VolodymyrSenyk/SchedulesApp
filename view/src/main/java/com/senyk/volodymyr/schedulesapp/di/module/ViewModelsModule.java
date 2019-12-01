package com.senyk.volodymyr.schedulesapp.di.module;

import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ErrorsHandlerModule;
import com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui.DtoUiListsMappersModule;
import com.senyk.volodymyr.schedulesapp.di.module.mappers.uilistupdater.UiListUpdatersModule;
import com.senyk.volodymyr.schedulesapp.di.module.repository.RepositoriesModule;
import com.senyk.volodymyr.schedulesapp.model.models.dto.PairDto;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.GenericDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.SchedulesDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.uilistupdater.SchedulesUiListUpdater;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.PairUi;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.factories.ViewModelFactory;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule.DayScheduleViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule.WeekViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.NewScheduleCreatorViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.SchedulesManagerViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesNavigationSharedViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module(includes = {
        ErrorsHandlerModule.class,
        RepositoriesModule.class,
        DtoUiListsMappersModule.class,
        UiListUpdatersModule.class
})
public class ViewModelsModule {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory bindViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(SchedulesNavigationSharedViewModel.class)
    ViewModel bindSchedulesNavigationSharedViewModel() {
        return new SchedulesNavigationSharedViewModel();
    }

    @Provides
    @IntoMap
    @ViewModelKey(DayScheduleViewModel.class)
    ViewModel bindDayScheduleViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            GenericDtoUiListMapper<PairDto, PairUi> pairDtoUiUiListMapper) {
        return new DayScheduleViewModel(
                errorsHandler,
                schedulesRepository,
                pairDtoUiUiListMapper
        );
    }

    @Provides
    @IntoMap
    @ViewModelKey(NewScheduleCreatorViewModel.class)
    ViewModel bindNewScheduleCreatorViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            UserSettingsRepository userSettingsRepository,
            ScheduleDtoUiMapper scheduleMapper) {
        return new NewScheduleCreatorViewModel(
                errorsHandler,
                schedulesRepository,
                userSettingsRepository,
                scheduleMapper
        );
    }

    @Provides
    @IntoMap
    @ViewModelKey(SchedulesManagerViewModel.class)
    ViewModel bindSchedulesManagerViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            UserSettingsRepository userSettingsRepository,
            SchedulesDtoUiListMapper schedulesMapper,
            SchedulesUiListUpdater schedulesListChanger
    ) {
        return new SchedulesManagerViewModel(
                errorsHandler,
                schedulesRepository,
                userSettingsRepository,
                schedulesMapper,
                schedulesListChanger
        );
    }

    @Provides
    @IntoMap
    @ViewModelKey(WeekViewModel.class)
    ViewModel bindWeekViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            SchedulesDtoUiListMapper schedulesMapper
    ) {
        return new WeekViewModel(
                errorsHandler,
                schedulesRepository,
                schedulesMapper
        );
    }

}
