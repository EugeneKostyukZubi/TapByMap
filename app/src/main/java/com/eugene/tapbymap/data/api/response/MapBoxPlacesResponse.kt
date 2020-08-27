package com.eugene.tapbymap.data.api.response

import com.eugene.tapbymap.data.api.entity.FeatureEntity

/**
 * Created by Eugene on 27.08.2020
 *
 * Data type of api response
 *
 * @link https://docs.mapbox.com/api/search/#geocoding
 * */
data class MapBoxPlacesResponse (
    val type: String,
    val query: List<Double>,
    val features: List<FeatureEntity>,
    val attribution: String
)