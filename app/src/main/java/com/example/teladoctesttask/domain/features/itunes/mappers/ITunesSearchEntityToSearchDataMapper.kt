package com.example.teladoctesttask.domain.features.itunes.mappers

import com.example.teladoctesttask.data.features.itunes.models.ITunesSearchDataEntity
import com.example.teladoctesttask.domain.base.Mapper
import com.example.teladoctesttask.domain.features.itunes.models.ITunesSearchData
import javax.inject.Inject

class ITunesSearchEntityToSearchDataMapper @Inject constructor() :
    Mapper<ITunesSearchDataEntity.Result, ITunesSearchData>() {

    override fun map(input: ITunesSearchDataEntity.Result): ITunesSearchData {
        return ITunesSearchData(
            artistName = input.artistName,
            collectionName = input.collectionName
        )
    }
}