package com.example.teladoctesttask.di

import com.example.teladoctesttask.data.features.poems.datasource.local.PoemsLocalDataSource
import com.example.teladoctesttask.data.features.poems.repository.PoemsRepository
import com.example.teladoctesttask.data.features.poems.repository.PoemsRepositoryImpl
import com.example.teladoctesttask.domain.features.poems.PoemsInteractor
import com.example.teladoctesttask.domain.features.poems.mappers.PoemMapToPoemDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Named("test_interactor")
    fun providePoemsInteractor(): PoemsInteractor {
        return Mockito.mock(PoemsInteractor::class.java)
    }

    @Provides
    @Named("test_repository")
    fun providePoemsRepository(
        localDataSource: PoemsLocalDataSource
    ): PoemsRepository {
        return Mockito.mock(PoemsRepositoryImpl::class.java)
    }
}