package com.example.teladoctesttask.data.network.rest.responseHandling.adapter

import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiError
import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiResponse
import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiResponseCall
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class RestApiResponseAdapter<S : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, RestApiError>
) : CallAdapter<S, Call<RestApiResponse<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<RestApiResponse<S>> {
        return RestApiResponseCall(call, errorBodyConverter)
    }
}