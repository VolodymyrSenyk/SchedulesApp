package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class BaseDtoUiListMapper<DTO, UI> {
    protected final BaseDtoUiMapper<DTO, UI> listItemMapper;

    public BaseDtoUiListMapper(@NotNull BaseDtoUiMapper<DTO, UI> mapper) {
        this.listItemMapper = mapper;
    }

    public abstract List<UI> convertToUi(@NotNull List<DTO> list);

    public abstract List<DTO> convertToDto(@NotNull List<UI> list);
}
