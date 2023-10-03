package com.example.teladoctesttask.data.network.rest.base.response

import com.example.teladoctesttask.data.network.rest.responseHandling.RestApiError
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
open class BaseRestResponse<T> {
    @SerializedName("data")
    var data: T? = null

    @SerializedName("error")
    var error: RestApiError? = null

    @SerializedName("success")
    var isSuccess: Boolean = false
}