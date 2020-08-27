package com.eugene.tapbymap.model

/**
 * Created by Eugene on 27.08.2020
 */
data class Place(
    val title: String,
    val name: String,
    val address: String?,
    val longitude: Double,
    val latitude: Double
)