package com.example.teladoctesttask.domain.features.itunes.models

sealed class ITunesSearchError {
    data class BackendError(val message: String) : ITunesSearchError()
    data object NetworkIssue : ITunesSearchError()
}