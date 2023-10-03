package com.example.teladoctesttask.utils.assets

import java.io.InputStream

interface AssetsManager {

    fun getFileByName(name: String): InputStream?
}