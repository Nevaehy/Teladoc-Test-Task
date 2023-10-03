package com.example.teladoctesttask.presentation.features.itunes.mappers

import com.example.teladoctesttask.data.base.Mapper
import com.example.teladoctesttask.domain.features.itunes.models.ITunesSearchData
import com.example.teladoctesttask.presentation.features.itunes.models.AlbumData
import javax.inject.Inject

class ITunesSearchDataToAlbumDataMapper @Inject constructor() :
    Mapper<ITunesSearchData, AlbumData>() {

    override fun map(input: ITunesSearchData): AlbumData {
        return AlbumData(
            name = input.collectionName,
            artist = input.artistName
        )
    }
}