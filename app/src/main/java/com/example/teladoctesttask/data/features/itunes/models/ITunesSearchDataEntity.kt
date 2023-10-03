package com.example.teladoctesttask.data.features.itunes.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ITunesSearchDataEntity(
    @SerializedName("results")
    val results: List<Result>
) {

    data class Result(
        @SerializedName("artistName")
        val artistName: String,
        @SerializedName("collectionName")
        val collectionName: String
    )
}