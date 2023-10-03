package com.example.teladoctesttask.data.features.itunes.datasource.local

import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchRequest

interface ITunesLocalDataSource {

    suspend fun getAlbumsByArtist(requestData: ITunesSearchRequest): ITunesSearchDataEntity?
    suspend fun saveAlbumsByArtist(requestData: ITunesSearchRequest, dataTestData: ITunesSearchDataEntity)
    fun clearAllCache()
}