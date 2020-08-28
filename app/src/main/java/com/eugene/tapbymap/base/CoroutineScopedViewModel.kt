package com.eugene.tapbymap.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Eugene on 2019-11-16
 */
abstract class CoroutineScopedViewModel : ViewModel(), CoroutineScope {

    private val parentJob = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("ViewModel", throwable.message ?: "empty")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + parentJob + exceptionHandler

    override fun onCleared() {
        parentJob.cancel()
    }
}