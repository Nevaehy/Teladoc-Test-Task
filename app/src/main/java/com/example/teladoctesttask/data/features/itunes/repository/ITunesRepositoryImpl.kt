package com.example.teladoctesttask.data.features.itunes.repository

import com.example.teladoctesttask.data.base.BaseRepository
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchRequest
import com.example.teladoctesttask.data.features.itunes.datasource.local.ITunesLocalDataSource
import com.example.teladoctesttask.data.features.itunes.datasource.remote.ITunesRemoteDataSource
import javax.inject.Inject

class ITunesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ITunesRemoteDataSource,
    private val localDataSource: ITunesLocalDataSource
) : ITunesRepository, BaseRepository() {

    override suspend fun findAlbumsByArtist(requestData: ITunesSearchRequest): ITunesSearchDataEntity {
        localDataSource.getAlbumsByArtist(requestData)?.let {
            return it
        }

        val response = handleRestResponse(remoteDataSource.getAlbumsByArtist(requestData))

        return response.also {
            localDataSource.saveAlbumsByArtist(requestData, it)
        }
    }

    override fun clearAllCache() {
        localDataSource.clearAllCache()
    }
}