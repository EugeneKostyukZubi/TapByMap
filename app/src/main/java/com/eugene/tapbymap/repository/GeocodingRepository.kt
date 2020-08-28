package com.eugene.tapbymap.repository

import com.eugene.tapbymap.model.Place

/**
 * Created by Eugene on 27.08.2020
 */
interface GeocodingRepository {
    suspend fun getPlaces(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ): Place
}