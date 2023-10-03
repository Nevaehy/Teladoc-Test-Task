package com.example.teladoctesttask.data.features.itunes.datasource.remote

import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiResponse
import com.example.teladoctesttask.data.features.itunes.ITunesRestApi
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.data.features.itunes.urlprovider.ITunesUrlProvider
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchRequest
import javax.inject.Inject

class ITunesRemoteDataSourceImpl @Inject constructor(
    private val restApi: ITunesRestApi,
    private val urlProvider: ITunesUrlProvider,
) : ITunesRemoteDataSource {

    override suspend fun getAlbumsByArtist(
        requestData: ITunesSearchRequest
    ): RestApiResponse<ITunesSearchDataEntity> {
        return restApi.getAlbumsByArtistName(
            url = urlProvider.getSearchUrl().toString(),
            artistName = requestData.artistName,
            entity = requestData.entity
        )
    }
}