package com.example.teladoctesttask.data.features.itunes.urlprovider

import com.example.teladoctesttask.di.NetworkModule.BASE_URL
import java.net.URL
import javax.inject.Inject

class ITunesUrlProviderImpl @Inject constructor() : ITunesUrlProvider {

    override fun getSearchUrl(): URL {
        return URL("$BASE_URL$SEARCH_URL")
    }

    companion object {
        private const val SEARCH_URL = "search"
    }
}