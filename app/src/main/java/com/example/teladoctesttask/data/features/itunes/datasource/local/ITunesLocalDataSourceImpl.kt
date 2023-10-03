package com.example.teladoctesttask.data.features.itunes.datasource.local

import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchRequest
import com.example.teladoctesttask.utils.collections.LimitedHashMap
import javax.inject.Inject

class ITunesLocalDataSourceImpl @Inject constructor() : ITunesLocalDataSource {

    private val searchCache: LimitedHashMap<ITunesSearchRequest, ITunesSearchDataEntity> = LimitedHashMap(MAX_CACHE_SIZE)

    override suspend fun getAlbumsByArtist(requestData: ITunesSearchRequest): ITunesSearchDataEntity? {
        return searchCache[requestData]
    }

    override suspend fun saveAlbumsByArtist(requestData: ITunesSearchRequest, dataTestData: ITunesSearchDataEntity) {
        searchCache[requestData] = dataTestData
    }

    override fun clearAllCache() {
        searchCache.clear()
    }

    companion object {
        private const val MAX_CACHE_SIZE = 10
    }
}