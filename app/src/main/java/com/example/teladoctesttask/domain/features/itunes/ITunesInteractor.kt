package com.example.teladoctesttask.domain.features.itunes

import com.example.teladoctesttask.data.base.NetworkErrorException
import com.example.teladoctesttask.data.base.RestApiException
import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchRequest
import com.example.teladoctesttask.data.features.itunes.repository.ITunesRepository
import com.example.teladoctesttask.domain.base.BaseInteractor
import com.example.teladoctesttask.domain.base.Outcome
import com.example.teladoctesttask.domain.features.itunes.mappers.ITunesSearchEntityToSearchDataMapper
import com.example.teladoctesttask.domain.features.itunes.models.ITunesSearchData
import com.example.teladoctesttask.domain.features.itunes.models.ITunesSearchError
import com.example.teladoctesttask.utils.extensions.toQuery
import javax.inject.Inject

class ITunesInteractor @Inject constructor(
    private val iTunesRepository: ITunesRepository,
    private val poemDataMapper: ITunesSearchEntityToSearchDataMapper
) : BaseInteractor() {

    suspend fun findAlbumsByArtistName(
        artistName: String,
        collectionType: CollectionType = CollectionType.ALBUM
    ): Outcome<List<ITunesSearchData>, ITunesSearchError> {
        return withIOContext {
            return@withIOContext try {
                val data = iTunesRepository.findAlbumsByArtist(
                    ITunesSearchRequest(
                        artistName = artistName.toQuery(),
                        entity = collectionType.query
                    )
                )
                val filtered = data.results.filter { it.artistName.toQuery() == artistName.toQuery() }
                Outcome.Success(poemDataMapper.mapList(filtered))
            } catch (e: RestApiException) {
                Outcome.Error(ITunesSearchError.BackendError(e.message))
            } catch (e: NetworkErrorException) {
                Outcome.Error(ITunesSearchError.NetworkIssue)
            }
        }
    }

    enum class CollectionType(val query: String) {
        ALBUM("album")
    }

    fun clearAllCache() {
        iTunesRepository.clearAllCache()
    }
}