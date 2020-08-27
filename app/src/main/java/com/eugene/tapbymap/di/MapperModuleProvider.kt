package com.eugene.tapbymap.di

import com.eugene.tapbymap.di.base.ModuleProvider
import com.eugene.tapbymap.data.mapper.PlaceMapper
import org.koin.dsl.module

/**
 * Created by Eugene on 27.08.2020
 */
object MapperModuleProvider : ModuleProvider {
    override fun provide() = module {
        single { PlaceMapper() }
    }
}