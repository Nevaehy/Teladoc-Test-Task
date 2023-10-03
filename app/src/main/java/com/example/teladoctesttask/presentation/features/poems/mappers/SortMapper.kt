package com.example.teladoctesttask.presentation.features.poems.mappers

import com.example.teladoctesttask.data.base.Mapper
import com.example.teladoctesttask.domain.features.poems.PoemsInteractorImpl
import com.example.teladoctesttask.presentation.features.poems.PoemsViewModel
import javax.inject.Inject

class SortMapper @Inject constructor() : Mapper<PoemsViewModel.Sort, PoemsInteractorImpl.Sort>() {

    override fun map(input: PoemsViewModel.Sort): PoemsInteractorImpl.Sort {
        return when (input) {
            PoemsViewModel.Sort.FREQUENCY -> PoemsInteractorImpl.Sort.FREQUENCY
            PoemsViewModel.Sort.ALPHABET -> PoemsInteractorImpl.Sort.ALPHABET
            PoemsViewModel.Sort.WORD_LENGTH -> PoemsInteractorImpl.Sort.WORD_LENGTH
            else -> PoemsInteractorImpl.Sort.FREQUENCY
        }
    }
}