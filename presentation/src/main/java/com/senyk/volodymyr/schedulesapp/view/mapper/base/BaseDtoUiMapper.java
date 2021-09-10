package com.senyk.volodymyr.schedulesapp.view.mapper.base;

public abstract class BaseDtoUiMapper<DTO, UI> {
    public abstract UI convertToUi(DTO dto);

    public abstract DTO convertToDto(UI uiModel);
}
