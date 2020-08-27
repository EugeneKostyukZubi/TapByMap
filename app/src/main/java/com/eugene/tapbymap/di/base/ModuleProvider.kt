package com.eugene.tapbymap.di.base

import org.koin.core.module.Module

/**
 * Created by Eugene on 2019-11-22
 */
interface ModuleProvider {
    fun provide(): Module
}