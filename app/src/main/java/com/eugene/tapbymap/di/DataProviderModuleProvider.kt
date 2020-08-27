package com.eugene.tapbymap.di

import com.eugene.tapbymap.data.mapbox.MapboxGeocodingProvider
import com.eugene.tapbymap.di.base.ModuleProvider
import org.koin.dsl.module

/**
 * Created by Eugene on 27.08.2020
 */
object DataProviderModuleProvider : ModuleProvider {
    override fun provide() = module {
        single { MapboxGeocodingProvider() }
    }
}