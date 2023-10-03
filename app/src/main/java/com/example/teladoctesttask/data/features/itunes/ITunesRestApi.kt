package com.example.teladoctesttask.data.features.itunes

import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ITunesRestApi {

    @GET
    suspend fun getAlbumsByArtistName(
        @Url url: String,
        @Query("term") artistName: String,
        @Query("entity") entity: String,
    ): RestApiResponse<ITunesSearchDataEntity>
}