package com.eugene.tapbymap.di

import com.eugene.tapbymap.BuildConfig
import com.eugene.tapbymap.data.api.MapBoxApi
import com.eugene.tapbymap.di.base.ModuleProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Eugene on 27.08.2020
 */
object ApiModuleProvider : ModuleProvider {
    private val baseUrl = "https://api.mapbox.com/"

    override fun provide() = module {
        /**
         * Creates OkHttpClient
         * Adds LoggingInterceptor if app is debug
         * */
        single {
            val builder = OkHttpClient()
                .newBuilder()
                .followRedirects(false)

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            builder.build()
        }

        single<MapBoxApi> {
            Retrofit.Builder()
                .client(get())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MapBoxApi::class.java)
        }
    }
}