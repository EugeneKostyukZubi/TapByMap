package com.eugene.tapbymap.data.mapbox

import com.mapbox.api.geocoding.v5.GeocodingCriteria
import com.mapbox.api.geocoding.v5.MapboxGeocoding
import com.mapbox.api.geocoding.v5.models.CarmenFeature
import com.mapbox.api.geocoding.v5.models.GeocodingResponse
import com.mapbox.geojson.Point
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Eugene on 27.08.2020
 */
class MapboxGeocodingProvider {

    suspend fun reverseGeocodeAddress(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ) : CarmenFeature? = reverseGeocode(
        longitude,
        latitude,
        accessToken,
        GeocodingCriteria.TYPE_ADDRESS
    )

    suspend fun reverseGeocodePoi(
        longitude: Double,
        latitude: Double,
        accessToken: String
    ) : CarmenFeature? = reverseGeocode(
        longitude,
        latitude,
        accessToken,
        GeocodingCriteria.TYPE_POI
    )

    private suspend fun reverseGeocode(
        longitude: Double,
        latitude: Double,
        accessToken: String,
        type: String
    ): CarmenFeature? =
        suspendCoroutine { continuation ->
            getGeocodingClient(longitude, latitude, accessToken, type).enqueueCall(
                object : Callback<GeocodingResponse> {
                    override fun onResponse(
                        call: Call<GeocodingResponse>,
                        response: Response<GeocodingResponse>
                    ) {
                        response.body()?.let { responseBody ->
                            responseBody.features()
                                .takeIf { it.isNotEmpty() }
                                ?.first()
                                ?.run {
                                    continuation.resume(this)
                                } ?: continuation.resume(null)
                        } ?: continuation.resume(null)
                    }

                    override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                }
            )
        }

    private fun getGeocodingClient(longitude: Double, latitude: Double, accessToken: String, type: String) =
        MapboxGeocoding.builder()
            .accessToken(accessToken)
            .query(Point.fromLngLat(longitude, latitude))
            .geocodingTypes(type)
            .build()

}