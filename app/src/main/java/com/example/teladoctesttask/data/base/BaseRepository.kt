package com.example.teladoctesttask.data.base

import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiResponse

abstract class BaseRepository {

    fun <T : Any> handleRestResponse(response: RestApiResponse<T>): T {
        return when (response) {
            is RestApiResponse.ApiError -> {
                val errorMessage = response.body.error?.message
                    ?: response.body.message
                    ?: ""

                val errorCode = response.body.error?.code ?: response.code
                throw RestApiException(errorMessage, errorCode)
            }
            is RestApiResponse.NetworkError -> {
                throw NetworkErrorException()
            }
            is RestApiResponse.UnknownError -> {
                throw RestApiException("oops something went wrong [${response.code}]", response.code)
            }
            is RestApiResponse.Success -> response.body
        }
    }
}