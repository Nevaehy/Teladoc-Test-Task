package com.example.teladoctesttask.di

import com.example.teladoctesttask.data.features.itunes.datasource.local.ITunesLocalDataSource
import com.example.teladoctesttask.data.features.itunes.datasource.local.ITunesLocalDataSourceImpl
import com.example.teladoctesttask.data.features.itunes.datasource.remote.ITunesRemoteDataSource
import com.example.teladoctesttask.data.features.itunes.datasource.remote.ITunesRemoteDataSourceImpl
import com.example.teladoctesttask.data.features.itunes.repository.ITunesRepository
import com.example.teladoctesttask.data.features.itunes.repository.ITunesRepositoryImpl
import com.example.teladoctesttask.data.features.itunes.urlprovider.ITunesUrlProvider
import com.example.teladoctesttask.data.features.itunes.urlprovider.ITunesUrlProviderImpl
import com.example.teladoctesttask.data.features.poems.datasource.local.PoemsLocalDataSource
import com.example.teladoctesttask.data.features.poems.datasource.local.PoemsLocalDataSourceImpl
import com.example.teladoctesttask.data.features.poems.repository.PoemsRepository
import com.example.teladoctesttask.data.features.poems.repository.PoemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun providePoemsRepository(repositoryImpl: PoemsRepositoryImpl): PoemsRepository

    @Binds
    fun providePoemsLocalDataSource(dataSourceImpl: PoemsLocalDataSourceImpl): PoemsLocalDataSource

    @Binds
    fun provideITunesRepository(repositoryImpl: ITunesRepositoryImpl): ITunesRepository

    @Binds
    fun provideITunesLocalDataSource(dataSourceImpl: ITunesLocalDataSourceImpl): ITunesLocalDataSource

    @Binds
    fun provideITunesRemoteDataSource(dataSourceImpl: ITunesRemoteDataSourceImpl): ITunesRemoteDataSource
}