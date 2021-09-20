package com.senykvolodymyr.presentation.domain.repository

import com.senykvolodymyr.presentation.domain.entity.DayType
import com.senykvolodymyr.presentation.domain.entity.Pair
import com.senykvolodymyr.presentation.domain.entity.WeekType
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
