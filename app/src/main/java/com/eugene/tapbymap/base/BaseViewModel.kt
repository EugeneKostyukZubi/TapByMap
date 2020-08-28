package com.eugene.tapbymap.base

import com.eugene.tapbymap.extensions.event
import com.eugene.tapbymap.extensions.mutable
import org.koin.core.KoinComponent

/**
 * Created by Eugene on 2019-11-22
 */

abstract class BaseViewModel : CoroutineScopedViewModel(), KoinComponent {

    val isLoading = mutable(false)
    val onErrorEvent = event<Exception>()
}