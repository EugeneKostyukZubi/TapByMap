package com.eugene.tapbymap.extensions

import androidx.lifecycle.MutableLiveData

suspend inline fun withProgress(
    liveData: MutableLiveData<Boolean>,
    crossinline block: suspend () -> Unit
) {
    try {
        liveData.postValue(true)
        block()
    } finally {
        liveData.postValue(false)
    }
}
