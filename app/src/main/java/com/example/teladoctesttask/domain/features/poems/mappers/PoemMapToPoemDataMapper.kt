package com.example.teladoctesttask.domain.features.poems.mappers

import com.example.teladoctesttask.domain.base.Mapper
import com.example.teladoctesttask.domain.features.poems.models.PoemData
import javax.inject.Inject

class PoemMapToPoemDataMapper @Inject constructor() : Mapper<Map<String, Int>, List<PoemData>>() {

    override fun map(input: Map<String, Int>): List<PoemData> {
        return input.map {
            PoemData(
                word = it.key,
                occurrence = it.value
            )
        }
    }
}