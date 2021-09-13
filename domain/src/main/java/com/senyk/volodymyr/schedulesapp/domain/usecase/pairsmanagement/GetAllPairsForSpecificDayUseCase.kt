package com.senyk.volodymyr.schedulesapp.domain.usecase.pairsmanagement

import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.domain.repository.PairRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GetAllPairsForSpecificDayUseCase @Inject constructor(
    private val pairRepository: PairRepository
) {

    operator fun invoke(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType
    ): Single<List<Pair>> = pairRepository.getAllPairsByFilters(scheduleId, weekType, dayType)
}
