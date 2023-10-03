package com.example.teladoctesttask.helper

import com.example.teladoctesttask.data.features.poems.repository.PoemsRepository
import com.example.teladoctesttask.domain.features.poems.PoemsInteractor
import com.example.teladoctesttask.domain.features.poems.mappers.PoemMapToPoemDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    fun providePoemsInteractor(
        poemsRepository: PoemsRepository,
        poemDataMapper: PoemMapToPoemDataMapper
    ): PoemsInteractor {
        return Mockito.mock(PoemsInteractor::class.java)
    }
}