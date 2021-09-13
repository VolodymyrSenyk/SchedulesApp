package com.senyk.volodymyr.schedulesapp.data.repository

import com.senyk.volodymyr.schedulesapp.data.datasource.database.dao.PairDao
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toPair
import com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.toPairDataEntity
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType
import com.senyk.volodymyr.schedulesapp.domain.repository.PairRepository
import io.reactivex.Completable
import io.reactivex.Maybe
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
