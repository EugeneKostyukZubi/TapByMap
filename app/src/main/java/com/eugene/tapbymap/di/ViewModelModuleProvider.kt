package com.eugene.tapbymap.di

import com.eugene.tapbymap.di.base.ModuleProvider
import com.eugene.tapbymap.presentation.history.HistoryViewModel
import com.eugene.tapbymap.presentation.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Eugene on 27.08.2020
 */
object ViewModelModuleProvider : ModuleProvider {
    override fun provide() = module {
        viewModel { MapViewModel() }
        viewModel { HistoryViewModel() }
    }
}