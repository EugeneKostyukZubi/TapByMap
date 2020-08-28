package com.eugene.tapbymap

import android.app.Application
import com.eugene.tapbymap.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Eugene on 27.08.2020
 */
class TapByMapApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidContext(this@TapByMapApplication)
            modules(
                listOf(
                    ApiModuleProvider.provide(),
                    MapperModuleProvider.provide(),
                    RepositoryModuleProvider.provide(),
                    ViewModelModuleProvider.provide(),
                    DataProviderModuleProvider.provide()
                )
            )
        }
    }
}