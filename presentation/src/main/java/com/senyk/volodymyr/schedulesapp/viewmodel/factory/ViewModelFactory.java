package com.senyk.volodymyr.schedulesapp.viewmodel.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.senyk.volodymyr.schedulesapp.view.exception.NoSuchViewModelClassException;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.annotations.NonNull;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        this.providerMap = providerMap;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (providerMap.get(modelClass) != null) {
            return (T) providerMap.get(modelClass).get();
        } else {
            throw new NoSuchViewModelClassException();
        }
    }
}
