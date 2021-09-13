package com.senyk.volodymyr.schedulesapp.domain.repository

import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue

interface PairRepository {

    @CheckReturnValue
    fun getAllPairsByFilters(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType
    ): Single<List<Pair>>

    @CheckReturnValue
    fun updateAllPairsByFilters(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType,
        pairs: List<Pair>
    ): Completable
}
