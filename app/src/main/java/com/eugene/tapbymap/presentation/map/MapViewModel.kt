package com.eugene.tapbymap.presentation.map

import android.content.Context
import com.eugene.tapbymap.R
import com.eugene.tapbymap.base.BaseViewModel
import com.eugene.tapbymap.base.Event
import com.eugene.tapbymap.extensions.event
import com.eugene.tapbymap.model.Place
import com.eugene.tapbymap.repository.GeocodingRepository
import kotlinx.coroutines.launch
import org.koin.core.inject

/**
 * Created by Eugene on 27.08.2020
 */
class MapViewModel : BaseViewModel() {

    private val geocodingRepository: GeocodingRepository by inject()

    val onPlaceFoundEvent = event<Place>()

    fun getPlaces(
        context: Context,
        longitude: Double,
        latitude: Double,
        accessToken: String
    ) = launch {
        try {
            geocodingRepository.getPlaces(
                longitude, latitude, accessToken
            ).run {
                onPlaceFoundEvent.postValue(
                    Event(
                        handlePlace(context, this)
                    )
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            errorEvent.postValue(Event(ex))
        }
    }

    private fun handlePlace(context: Context, place: Place): Place {
        return Place(
            handleTitle(context, place.title),
            handleName(context, place.name),
            handleAddress(context, place.address),
            place.longitude,
            place.latitude
        )
    }

    private fun handleTitle(context: Context, title: String): String {
        return String.format(
            context.getString(R.string.nearest_place_template),
            title
        )
    }

    private fun handleAddress(context: Context, address: String?): String {
        return String.format(
            context.getString(R.string.address_template),
            address ?: context.getString(R.string.was_not_defined)
        )
    }

    private fun handleName(context: Context, name: String): String {
        return String.format(context.getString(R.string.poi_template), name)
    }
}