package com.example.teladoctesttask.utils.assets

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class AssetsManagerImpl @Inject constructor(
    @ApplicationContext private val ctx: Context
) : AssetsManager {

    override fun getFileByName(name: String): InputStream? {
        return try {
            ctx.assets.open(name)
        } catch (e: Exception) {
            null
        }
    }
}