package com.example.teladoctesttask.domain.features.poems

import com.example.teladoctesttask.data.features.poems.repository.PoemsRepository
import com.example.teladoctesttask.domain.base.BaseInteractor
import com.example.teladoctesttask.domain.base.Outcome
import com.example.teladoctesttask.domain.features.poems.mappers.PoemMapToPoemDataMapper
import com.example.teladoctesttask.domain.features.poems.models.PoemData
import com.example.teladoctesttask.domain.features.poems.models.PoemError
import javax.inject.Inject

class PoemsInteractorImpl @Inject constructor(
    private val poemsRepository: PoemsRepository,
    private val poemDataMapper: PoemMapToPoemDataMapper
) : PoemsInteractor, BaseInteractor() {

    private var poemsData: List<PoemData> = listOf()

    override suspend fun getPoemData(
        poemName: String
    ): Outcome<List<PoemData>, PoemError> {
        return withIOContext {
            return@withIOContext try {
                if (poemsData.isEmpty()) {
                    val data = poemsRepository.getPoemWordsOccurrences(poemName)
                    poemsData = poemDataMapper.map(data)
                    poemsData = getSortedPoemData(Sort.FREQUENCY)
                }
                Outcome.Success(poemsData)
            } catch (e: Exception) {
                Outcome.Error(PoemError.GeneralError)
            }
        }
    }

    // actually, we can keep this logic inside a ViewModel to get rid of unwanted mappings
    // but in that case we would slightly violate the Clean Architecture's separation of concerns...
    // so there is always a trade-off between performance and following the coding principles
    override suspend fun getSortedPoemData(sortType: Sort): List<PoemData> {
        return withComputationsContext {
            when (sortType) {
                Sort.FREQUENCY -> poemsData.sortedByDescending { it.occurrence }
                Sort.ALPHABET -> poemsData.sortedBy { it.word }
                Sort.WORD_LENGTH -> poemsData.sortedBy { it.word.length }
            }
        }
    }

    enum class Sort {
        FREQUENCY,
        ALPHABET,
        WORD_LENGTH
    }

    override fun clearAllCache() {
        poemsData = listOf()
    }
}