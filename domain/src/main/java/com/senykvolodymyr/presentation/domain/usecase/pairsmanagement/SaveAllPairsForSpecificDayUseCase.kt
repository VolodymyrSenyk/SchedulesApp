package com.senykvolodymyr.presentation.domain.usecase.pairsmanagement

import com.senykvolodymyr.presentation.domain.entity.DayType
import com.senykvolodymyr.presentation.domain.entity.Pair
import com.senykvolodymyr.presentation.domain.entity.WeekType
import com.senykvolodymyr.presentation.domain.repository.PairRepository
import io.reactivex.Single
import javax.inject.Inject

class SaveAllPairsForSpecificDayUseCase @Inject constructor(
    private val pairRepository: PairRepository
) {

    operator fun invoke(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType,
        pairs: List<Pair>
    ): Single<List<Pair>> =
        pairRepository.updateAllPairsByFilters(scheduleId, weekType, dayType, pairs)
            .andThen(pairRepository.getAllPairsByFilters(scheduleId, weekType, dayType))
}
