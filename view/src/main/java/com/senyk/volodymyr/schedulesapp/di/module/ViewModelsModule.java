package com.senyk.volodymyr.schedulesapp.di.module;

import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ErrorsHandlerModule;
import com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui.DtoUiComplicatedModelsMappersModule;
import com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui.DtoUiListsMappersModule;
import com.senyk.volodymyr.schedulesapp.di.module.repository.RepositoriesModule;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.DayDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtouilist.SchedulesDtoUiListMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.factories.ViewModelFactory;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.pairsmanagement.OneDayScheduleViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.pairsmanagement.StudyWeekViewModel;
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
        DtoUiComplicatedModelsMappersModule.class
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
    @ViewModelKey(OneDayScheduleViewModel.class)
    ViewModel bindOneDayScheduleViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            DayDtoUiMapper dayDtoUiMapper) {
        return new OneDayScheduleViewModel(
                errorsHandler,
                schedulesRepository,
                dayDtoUiMapper);
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
                scheduleMapper);
    }

    @Provides
    @IntoMap
    @ViewModelKey(SchedulesManagerViewModel.class)
    ViewModel bindSchedulesManagerViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            UserSettingsRepository userSettingsRepository,
            SchedulesDtoUiListMapper schedulesMapper) {
        return new SchedulesManagerViewModel(
                errorsHandler,
                schedulesRepository,
                userSettingsRepository,
                schedulesMapper);
    }

    @Provides
    @IntoMap
    @ViewModelKey(StudyWeekViewModel.class)
    ViewModel bindStudyWeekViewModel(
            ErrorsHandler errorsHandler,
            SchedulesRepository schedulesRepository,
            SchedulesDtoUiListMapper schedulesMapper) {
        return new StudyWeekViewModel(
                errorsHandler,
                schedulesRepository,
                schedulesMapper);
    }
}
