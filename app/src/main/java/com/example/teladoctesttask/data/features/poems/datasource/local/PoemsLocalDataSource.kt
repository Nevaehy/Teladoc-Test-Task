package com.example.teladoctesttask.data.features.poems.datasource.local

import java.io.InputStream

interface PoemsLocalDataSource {

    fun getPoem(fileName: String): InputStream?
}