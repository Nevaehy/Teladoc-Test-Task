package com.example.teladoctesttask.data.features.poems.repository

interface PoemsRepository {

    fun getPoemWordsOccurrences(poemName: String): Map<String, Int>
}