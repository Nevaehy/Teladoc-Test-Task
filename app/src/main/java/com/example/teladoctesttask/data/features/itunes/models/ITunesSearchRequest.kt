package com.example.teladoctesttask.data.features.itunes.models

import androidx.annotation.Keep

@Keep
data class ITunesSearchRequest(
    val artistName: String,
    val entity: String
)
