package com.example.teladoctesttask.di

import com.example.teladoctesttask.data.features.itunes.urlprovider.ITunesUrlProvider
import com.example.teladoctesttask.data.features.itunes.urlprovider.ITunesUrlProviderImpl
import com.example.teladoctesttask.data.network.rest.responseHandling.adapter.RestApiResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.teladoctesttask.data.features.itunes.ITunesRestApi
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.GzipSource
import okio.buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RestApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideITunesRestApi(retrofit: Retrofit): ITunesRestApi {
        return retrofit.create(ITunesRestApi::class.java)
    }

    @Provides
    fun provideITunesUrlProvider(impl: ITunesUrlProviderImpl): ITunesUrlProvider {
        return impl
    }

    const val BASE_URL = "https://itunes.apple.com/"
}