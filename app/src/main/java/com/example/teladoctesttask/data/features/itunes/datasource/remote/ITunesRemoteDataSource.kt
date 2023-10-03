package com.example.teladoctesttask.data.features.itunes.datasource.remote

import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiResponse
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchRequest

interface ITunesRemoteDataSource {

    suspend fun getAlbumsByArtist(requestData: ITunesSearchRequest): RestApiResponse<ITunesSearchDataEntity>

}