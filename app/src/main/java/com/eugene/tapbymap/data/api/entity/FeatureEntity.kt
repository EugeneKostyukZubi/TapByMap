package com.eugene.tapbymap.data.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Eugene on 27.08.2020
 */
data class FeatureEntity(
    val id: String,
    val type: String,
    @SerializedName("place_type")
    val placeType: List<String>,
    val relevance: Long,
    val properties: PropertiesEntity,
    val text: String,
    @SerializedName("place_name")
    val placeName: String,
    val center: List<Double>,
    val geometry: GeometryEntity,
    val address: String?,
    val context: List<ContextEntity>?,
    val bbox: List<Double>?
)