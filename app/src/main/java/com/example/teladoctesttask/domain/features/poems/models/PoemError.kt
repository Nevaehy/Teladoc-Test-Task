package com.example.teladoctesttask.domain.features.poems.models

sealed class PoemError {
    data object GeneralError : PoemError()
}