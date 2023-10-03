package com.example.teladoctesttask.presentation.features.poems.mappers

import com.example.teladoctesttask.data.base.Mapper
import com.example.teladoctesttask.domain.features.poems.models.PoemData
import com.example.teladoctesttask.presentation.features.poems.models.WordsOccurrencesData
import javax.inject.Inject

class PoemDataToWordsOccurrencesMapper @Inject constructor() :
    Mapper<PoemData, WordsOccurrencesData>() {

    override fun map(input: PoemData): WordsOccurrencesData {
        return WordsOccurrencesData(
            word = input.word,
            occurrence = input.occurrence
        )
    }
}