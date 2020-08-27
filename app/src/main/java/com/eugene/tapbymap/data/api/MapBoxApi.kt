package com.eugene.tapbymap.data.api

import com.eugene.tapbymap.data.api.response.MapBoxPlacesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Eugene on 27.08.2020
 *
 * @link https://docs.mapbox.com/api/search/#geocoding
 */
interface MapBoxApi {

    @GET("geocoding/v5/mapbox.places/{longitude},{latitude}.json")
    suspend fun getPlaces(
        @Path("longitude") longitude : Double,
        @Path("latitude") latitude : Double,
        @Query("access_token") accessToken : String,
        @Query("language") language : String? = null
    ) : MapBoxPlacesResponse
}