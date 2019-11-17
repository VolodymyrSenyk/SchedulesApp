package com.senyk.volodymyr.schedulesapp.di.module;

import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.schedulesapp.model.repository.SchedulesRepository;
import com.senyk.volodymyr.schedulesapp.model.repository.UserSettingsRepository;
import com.senyk.volodymyr.schedulesapp.viewmodel.helpers.ErrorsHandler;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.factories.ViewModelFactory;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.navigation.RedirectViewModule;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedule.DayScheduleViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.NewScheduleCreatorViewModel;
import com.senyk.volodymyr.schedulesapp.viewmodel.viewmodels.schedulesmanagement.SchedulesManagerViewModule;
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
        ResourcesProviderModule.class,
        ErrorsHandlerModule.class,
        RepositoriesModule.class
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
    ViewModel bindSchedulesAppSharedViewModel(ErrorsHandler errorsHandler) {
        return new SchedulesAppSharedViewModel(errorsHandler);
    }

    @Provides
    @IntoMap
    @ViewModelKey(RedirectViewModule.class)
    ViewModel bindRedirectViewModule(ErrorsHandler errorsHandler, UserSettingsRepository repository) {
        return new RedirectViewModule(errorsHandler, repository);
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
    ViewModel bindNewScheduleCreatorViewModel(ErrorsHandler errorsHandler, SchedulesRepository repository) {
        return new NewScheduleCreatorViewModel(errorsHandler, repository);
    }

    @Provides
    @IntoMap
    @ViewModelKey(SchedulesManagerViewModule.class)
    ViewModel bindSchedulesManagerViewModule(ErrorsHandler errorsHandler, SchedulesRepository repository) {
        return new SchedulesManagerViewModule(errorsHandler, repository);
    }

}
