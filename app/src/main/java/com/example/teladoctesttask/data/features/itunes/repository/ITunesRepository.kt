package com.example.teladoctesttask.data.features.itunes.repository

import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchRequest

interface ITunesRepository {

    suspend fun findAlbumsByArtist(requestData: ITunesSearchRequest): ITunesSearchDataEntity

    fun clearAllCache()
}