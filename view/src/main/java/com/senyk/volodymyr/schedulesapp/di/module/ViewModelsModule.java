package com.senyk.volodymyr.schedulesapp.di.module;

import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.schedulesapp.di.module.helpers.ErrorsHandlerModule;
import com.senyk.volodymyr.schedulesapp.di.module.mappers.dtoui.DtoUiListsMappersModule;
import com.senyk.volodymyr.schedulesapp.di.module.mappers.uilistupdater.UiListUpdatersModule;
import com.senyk.volodymyr.schedulesapp.di.module.repository.RepositoriesModule;
import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ResourcesProvider;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.dtoui.ScheduleDtoUiMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.factories.ViewModelFactory;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule.DayScheduleViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.NewScheduleCreatorViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.SchedulesManagerViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.shared.SchedulesAppSharedViewModel;

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
    @ViewModelKey(SchedulesAppSharedViewModel.class)
    ViewModel bindSharedViewModel() {
        return new SchedulesAppSharedViewModel();
    }

    @Provides
    @IntoMap
    @ViewModelKey(DayScheduleViewModel.class)
    ViewModel bindDayScheduleViewModel(ErrorsHandler errorsHandler, SchedulesRepository repository) {
        return new DayScheduleViewModel(errorsHandler, repository);
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
    ViewModel bindSchedulesManagerViewModule(ErrorsHandler errorsHandler, SchedulesRepository repository) {
        return new SchedulesManagerViewModel(errorsHandler, repository);
    }

}
