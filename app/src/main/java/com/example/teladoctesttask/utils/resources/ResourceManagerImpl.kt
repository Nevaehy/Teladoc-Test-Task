package com.example.teladoctesttask.utils.resources

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceManager {

    override fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }
}