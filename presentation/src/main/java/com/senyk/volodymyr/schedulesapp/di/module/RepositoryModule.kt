package com.senyk.volodymyr.schedulesapp.di.module

import com.senyk.volodymyr.schedulesapp.data.repository.PairRoomRepository
import com.senyk.volodymyr.schedulesapp.data.repository.ScheduleRoomRepository
import com.senyk.volodymyr.schedulesapp.domain.repository.PairRepository
import com.senyk.volodymyr.schedulesapp.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [PersistenceModule::class])
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindScheduleRepository(repository: ScheduleRoomRepository): ScheduleRepository

    @Singleton
    @Binds
    fun bindPairRepository(repository: PairRoomRepository): PairRepository
}
