package com.eugene.tapbymap.di

import com.eugene.tapbymap.di.base.ModuleProvider
import com.eugene.tapbymap.repository.GeocodingRepository
import com.eugene.tapbymap.data.storage.GeocodingStorage
import org.koin.dsl.module

/**
 * Created by Eugene on 27.08.2020
 */
object RepositoryModuleProvider : ModuleProvider {
    override fun provide() = module {
        single<GeocodingRepository> { GeocodingStorage() }
    }
}