package com.senykvolodymyr.presentation.di.module

import com.senykvolodymyr.presentation.data.repository.PairRoomRepository
import com.senykvolodymyr.presentation.data.repository.ScheduleRoomRepository
import com.senykvolodymyr.presentation.domain.repository.PairRepository
import com.senykvolodymyr.presentation.domain.repository.ScheduleRepository
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
