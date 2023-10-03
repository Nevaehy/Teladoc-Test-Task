package com.example.teladoctesttask.di

import com.example.teladoctesttask.utils.assets.AssetsManager
import com.example.teladoctesttask.utils.assets.AssetsManagerImpl
import com.example.teladoctesttask.utils.resources.ResourceManager
import com.example.teladoctesttask.utils.resources.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    fun provideResourceManager(managerImpl: ResourceManagerImpl): ResourceManager

    @Binds
    fun provideAssetsManager(managerImpl: AssetsManagerImpl): AssetsManager
}