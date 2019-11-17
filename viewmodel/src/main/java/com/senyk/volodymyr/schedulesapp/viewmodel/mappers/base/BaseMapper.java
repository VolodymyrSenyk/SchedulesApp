package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base;

public abstract class BaseMapper<FROM, TO> {
    public abstract TO convertTo(FROM item);

    public abstract FROM convertFrom(TO item);
}
