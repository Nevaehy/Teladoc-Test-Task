package com.example.teladoctesttask.di

import com.example.teladoctesttask.domain.features.poems.PoemsInteractor
import com.example.teladoctesttask.domain.features.poems.PoemsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface InteractorModule {

    @Binds
    fun providePoemsInteractor(interactorImpl: PoemsInteractorImpl): PoemsInteractor
}