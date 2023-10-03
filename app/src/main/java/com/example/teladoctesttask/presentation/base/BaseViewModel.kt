package com.example.teladoctesttask.presentation.base

import com.example.teladoctesttask.utils.resources.ResourceManager
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teladoctesttask.R
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    @Inject
    internal lateinit var resourceManager: ResourceManager

    val stateLiveData = MutableLiveData<State>()

    protected inline fun launch(
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        crossinline block: suspend CoroutineScope.() -> Unit
    ): Job {
        return (scope + CoroutineExceptionHandler { _, _ -> }).launch(context = dispatcher) {
            runCatching { block() }
                .exceptionOrNull()
                ?.let { handleException(it) }
        }
    }

    @PublishedApi
    internal open suspend fun handleException(exception: Throwable) {
        exception.printStackTrace()
        val message = exception.message
        if (message.isNullOrEmpty().not()) {
            stateLiveData.postValue(State.Error(message!!))
        } else {
            stateLiveData.postValue(State.Error(resourceManager.getString(R.string.common_error_message)))
        }
        // log analytics exception here
    }
}

sealed class State {
    open class Error(open val message: String? = null) : State()
    data object Loading : State()
    data object Content : State()
}