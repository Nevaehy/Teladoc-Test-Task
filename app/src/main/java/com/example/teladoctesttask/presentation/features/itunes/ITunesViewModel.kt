package com.example.teladoctesttask.presentation.features.itunes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.teladoctesttask.domain.base.Outcome
import com.example.teladoctesttask.domain.features.itunes.ITunesInteractor
import com.example.teladoctesttask.presentation.base.BaseViewModel
import com.example.teladoctesttask.presentation.base.State
import com.example.teladoctesttask.presentation.features.itunes.mappers.ITunesSearchDataToAlbumDataMapper
import com.example.teladoctesttask.presentation.features.itunes.models.AlbumData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job

@HiltViewModel
class ITunesViewModel @Inject constructor(
    private val iTunesInteractor: ITunesInteractor,
    private val iTunesSearchMapper: ITunesSearchDataToAlbumDataMapper
) : BaseViewModel() {

    private val albumsData = MutableLiveData<List<AlbumData>>()
    fun albumsData(): LiveData<List<AlbumData>> = albumsData

    private var albumsJob: Job? = null

    private var artistQuery = DEFAULT_ARTIST

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        if (artistQuery.isEmpty()) return

        albumsJob?.cancel()
        albumsJob = launch(viewModelScope) {
            stateLiveData.value = State.Loading

            when (val outcome = iTunesInteractor.findAlbumsByArtistName(artistQuery)) {
                is Outcome.Success -> {
                    stateLiveData.value = State.Content
                    albumsData.value = iTunesSearchMapper.mapList(outcome.data)
                }

                is Outcome.Error -> stateLiveData.value = State.Error(outcome.reason.toString())
                is Outcome.NetworkConnection -> stateLiveData.value =
                    State.Error(outcome.cause?.message)
            }
        }
    }

    fun onQueryChange(query: String) {
        artistQuery = query.ifEmpty { DEFAULT_ARTIST }
        fetchAlbums()
    }

    fun retry() {
        fetchAlbums()
    }

    override fun onCleared() {
        super.onCleared()
        iTunesInteractor.clearAllCache()
    }

    companion object {
        private const val DEFAULT_ARTIST = "The Beatles"
    }
}