package com.senykvolodymyr.presentation.data.repository

import com.senykvolodymyr.presentation.data.datasource.database.dao.PairDao
import com.senykvolodymyr.presentation.data.datasource.database.entity.toPair
import com.senykvolodymyr.presentation.data.datasource.database.entity.toPairDataEntity
import com.senykvolodymyr.presentation.domain.entity.DayType
import com.senykvolodymyr.presentation.domain.entity.Pair
import com.senykvolodymyr.presentation.domain.entity.WeekType
import com.senykvolodymyr.presentation.domain.repository.PairRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PairRoomRepository @Inject constructor(
    private val pairDao: PairDao
) : PairRepository {

    override fun getAllPairsByFilters(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType
    ): Single<List<Pair>> = pairDao.getByFilters(scheduleId, weekType, dayType)
        .map { pairsDbo -> pairsDbo.map { it.toPair() } }

    override fun updateAllPairsByFilters(
        scheduleId: String,
        weekType: WeekType,
        dayType: DayType,
        pairs: List<Pair>
    ): Completable = pairDao.deleteByFilters(scheduleId, weekType, dayType)
        .andThen {
            pairDao.insertAll(
                pairs.map {
                    it.toPairDataEntity(scheduleId.toLong(), weekType, dayType).copy(id = 0)
                }
            )
        }
}
