package com.eugene.tapbymap.data.storage

import com.eugene.tapbymap.data.api.MapBoxApi
import com.eugene.tapbymap.data.mapbox.MapboxGeocodingProvider
import com.eugene.tapbymap.data.mapper.PlaceMapper
import com.eugene.tapbymap.exception.NotFoundException
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
        return getFromSdk(longitude, latitude, accessToken)
    }

    /**
     * To try to find address of the specific point
     *
     * @return reversed place by address type
     * */
    @Deprecated("Using sdk implementation")
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

    /**
     * To try to find address of the specific point
     *
     * @return reversed place by address type if it is possible or
     * geocode by POI type
     * */
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
        ).takeIf { it?.properties()?.has(ADDRESS_KEY) ?: false }
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

    companion object {
        private const val ADDRESS_KEY = "address"
    }
}