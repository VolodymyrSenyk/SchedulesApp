package com.senyk.volodymyr.schedulesapp.viewmodel.mappers.pojotouimappers;

import com.senyk.volodymyr.schedulesapp.model.models.pojo.SchedulePOJO;
import com.senyk.volodymyr.schedulesapp.viewmodel.mappers.base.BaseMapper;
import com.senyk.volodymyr.schedulesapp.viewmodel.models.ui.ScheduleUI;

public class ScheduleMapper extends BaseMapper<SchedulePOJO, ScheduleUI> {
    @Override
    public ScheduleUI convertTo(SchedulePOJO item) {
        return null;
    }

    @Override
    public SchedulePOJO convertFrom(ScheduleUI item) {
        return null;
    }
}
