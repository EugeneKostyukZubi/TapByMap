package com.eugene.tapbymap.data.storage

import com.eugene.tapbymap.data.preferences.PreferencesHelper
import com.eugene.tapbymap.model.Place
import com.eugene.tapbymap.repository.PlaceRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Eugene on 28.08.2020
 */
class PlaceStorage : PlaceRepository, KoinComponent {

    private val preferencesHelper : PreferencesHelper by inject()

    override suspend fun getSearchHistory(): List<Place> {
        return preferencesHelper.searchHistory
    }

    override suspend fun addPlaceToHistory(place: Place) {
        preferencesHelper.addPlaceToHistory(place)
    }

    override suspend fun removeSearchHistory() {
        preferencesHelper.removeSearchHistory()
    }
}