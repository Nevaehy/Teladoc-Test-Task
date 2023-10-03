package com.example.teladoctesttask.data.features.poems.datasource.local

import com.example.teladoctesttask.utils.assets.AssetsManager
import java.io.InputStream
import javax.inject.Inject

class PoemsLocalDataSourceImpl @Inject constructor(
    private val assetsManager: AssetsManager
) : PoemsLocalDataSource {

    override fun getPoem(fileName: String): InputStream? {
        return assetsManager.getFileByName(fileName)
    }
}