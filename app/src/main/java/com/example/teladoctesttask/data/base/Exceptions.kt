package com.example.teladoctesttask.data.base

class NetworkErrorException : Exception()
class RestApiException(override val message: String, val errorCode: Int) : Exception()
class InternalException : Exception()