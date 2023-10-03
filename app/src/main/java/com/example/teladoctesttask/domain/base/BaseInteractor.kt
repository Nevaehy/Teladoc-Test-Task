package com.example.teladoctesttask.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseInteractor {

    private val ioDispatcher = Dispatchers.IO
    private val computationsDispatcher = Dispatchers.Default

    protected suspend fun <T> withIOContext(block: (suspend CoroutineScope.() -> T)): T {
        return withContext(ioDispatcher) {
            block()
        }
    }

    protected suspend fun <T> withComputationsContext(block: (suspend CoroutineScope.() -> T)): T {
        return withContext(computationsDispatcher) {
            block()
        }
    }
}