package com.eugene.tapbymap.repository

import com.eugene.tapbymap.model.Place

/**
 * Created by Eugene on 28.08.2020
 */
interface PlaceRepository {
    suspend fun getSearchHistory() : List<Place>
    suspend fun addPlaceToHistory(place: Place)
}