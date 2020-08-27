package com.eugene.tapbymap.data.storage

import android.util.Log
import android.util.Log.d
import com.eugene.tapbymap.data.api.MapBoxApi
import com.eugene.tapbymap.data.mapbox.MapboxGeocodingProvider
import com.eugene.tapbymap.data.mapper.PlaceMapper
import com.eugene.tapbymap.exception.NotFoundException
import com.eugene.tapbymap.extensions.Empty
import com.eugene.tapbymap.model.Place
import com.eugene.tapbymap.repository.GeocodingRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Eugene on 27.08.2020
 */
class GeocodingStorage : GeocodingRepository, KoinComponent {

    private val mapBoxApi : MapBoxApi by inject()
    private val mapboxGeocodingProvider : MapboxGeocodingProvider by inject()
    private val placeMapper : PlaceMapper by inject()

    override suspend fun getPlaces(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ): Place {
        return getFromApi(longitude, latitude, accessToken)
    }

    private suspend fun getFromApi(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ) : Place {
        return mapBoxApi.getPlaces(
            longitude, latitude, accessToken
        ).run {
            placeMapper.transform(this)
                ?: throw NotFoundException
        }
    }

    private suspend fun getFromSdk(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ) : Place {
        return reverseGeocodeAddress(longitude, latitude, accessToken)
            ?: reverseGeocodePoi(longitude, latitude, accessToken)
    }

    private suspend fun reverseGeocodeAddress(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ) : Place? {
        return mapboxGeocodingProvider.reverseGeocodeAddress(
            longitude, latitude, accessToken
        ).takeIf { it?.address() != null }
            ?.run {
                placeMapper.transform(this)
            }
    }

    private suspend fun reverseGeocodePoi(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ) : Place {
        return mapboxGeocodingProvider.reverseGeocodePoi(
            longitude, latitude, accessToken
        )?.run {
            placeMapper.transform(this)
        } ?: throw NotFoundException
    }

}