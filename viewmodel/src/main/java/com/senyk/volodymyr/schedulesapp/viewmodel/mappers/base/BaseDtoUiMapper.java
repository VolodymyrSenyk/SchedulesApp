package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base;

public abstract class BaseDtoUiMapper<DTO, UI> {
    public abstract UI convertToDto(DTO dto);

    public abstract DTO convertToUi(UI uiModel);
}
