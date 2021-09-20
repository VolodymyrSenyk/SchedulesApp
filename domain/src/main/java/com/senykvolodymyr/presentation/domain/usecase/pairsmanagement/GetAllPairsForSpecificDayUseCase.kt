package com.senykvolodymyr.presentation.domain.usecase.pairsmanagement

import com.senykvolodymyr.presentation.domain.entity.DayType
import com.senykvolodymyr.presentation.domain.entity.Pair
import com.senykvolodymyr.presentation.domain.entity.WeekType
import com.senykvolodymyr.presentation.domain.repository.PairRepository
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
