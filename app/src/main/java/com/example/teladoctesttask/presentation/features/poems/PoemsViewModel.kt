package com.example.teladoctesttask.presentation.features.poems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.teladoctesttask.domain.base.Outcome
import com.example.teladoctesttask.domain.features.poems.PoemsInteractor
import com.example.teladoctesttask.presentation.base.BaseViewModel
import com.example.teladoctesttask.presentation.base.State
import com.example.teladoctesttask.presentation.features.poems.mappers.PoemDataToWordsOccurrencesMapper
import com.example.teladoctesttask.presentation.features.poems.mappers.SortMapper
import com.example.teladoctesttask.presentation.features.poems.models.WordsOccurrencesData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job

@HiltViewModel
class PoemsViewModel @Inject constructor(
    private val poemsInteractor: PoemsInteractor,
    private val poemMapper: PoemDataToWordsOccurrencesMapper,
    private val sortMapper: SortMapper
) : BaseViewModel() {

    private val poemData = MutableLiveData<List<WordsOccurrencesData>>()
    fun poemData(): LiveData<List<WordsOccurrencesData>> = poemData

    private val lastSort = MutableLiveData(Sort.FREQUENCY)
    fun lastSort(): LiveData<Sort> = lastSort

    private var poemsJob: Job? = null
    private var sortJob: Job? = null

    init {
        fetchPoemData()
    }

    private fun fetchPoemData() {
        poemsJob?.cancel()
        poemsJob = launch(viewModelScope) {
            stateLiveData.value = State.Loading

            when (val outcome = poemsInteractor.getPoemData(POEM_NAME)) {
                is Outcome.Success -> {
                    stateLiveData.value = State.Content
                    poemData.value = poemMapper.mapList(outcome.data)
                }

                is Outcome.Error -> stateLiveData.value = State.Error(outcome.reason.toString())
                is Outcome.NetworkConnection -> stateLiveData.value =
                    State.Error(outcome.cause?.message)
            }
        }
    }

    fun retry() {
        fetchPoemData()
    }

    fun onSortClick(sortType: Sort) {
        sortData(sortType)
    }

    private fun sortData(sortType: Sort) {
        if (lastSort.value == sortType) return

        lastSort.value = sortType
        sortJob?.cancel()
        sortJob = launch(viewModelScope) {
            stateLiveData.value = State.Loading
            poemData.value = poemMapper.mapList(
                poemsInteractor.getSortedPoemData(
                    sortType = sortMapper.map(sortType)
                )
            )
            stateLiveData.value = State.Content
        }
    }

    override fun onCleared() {
        super.onCleared()
        poemsInteractor.clearAllCache()
    }

    enum class Sort {
        UNSPECIFIED,
        FREQUENCY,
        ALPHABET,
        WORD_LENGTH
    }

    companion object {
        private const val POEM_NAME = "Romeo-and-Juliet.txt"
    }
}