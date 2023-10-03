package com.example.teladoctesttask.domain.features.poems

import com.example.teladoctesttask.domain.base.Outcome
import com.example.teladoctesttask.domain.features.poems.models.PoemData
import com.example.teladoctesttask.domain.features.poems.models.PoemError

interface PoemsInteractor {

    suspend fun getPoemData(poemName: String): Outcome<List<PoemData>, PoemError>

    suspend fun getSortedPoemData(sortType: PoemsInteractorImpl.Sort): List<PoemData>

    fun clearAllCache()
}