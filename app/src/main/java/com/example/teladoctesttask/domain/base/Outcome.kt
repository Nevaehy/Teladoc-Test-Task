package com.example.teladoctesttask.domain.base

import com.example.teladoctesttask.data.base.NetworkErrorException

sealed class Outcome<out T : Any, out S : Any> {
    data class Success<out T : Any>(val data: T) : Outcome<T, Nothing>()
    data class Error<out S : Any>(val reason: S) : Outcome<Nothing, S>()
    class NetworkConnection(val cause: NetworkErrorException? = null) : Outcome<Nothing, Nothing>()
}