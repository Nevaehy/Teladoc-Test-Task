package com.example.teladoctesttask.data.network.rest.base.request

import com.google.gson.annotations.SerializedName

data class BaseRestRequest(
    @SerializedName("data") private val data: Any,
)