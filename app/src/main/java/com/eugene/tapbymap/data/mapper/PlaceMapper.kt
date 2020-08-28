package com.eugene.tapbymap.data.mapper

import com.eugene.tapbymap.data.api.entity.FeatureEntity
import com.eugene.tapbymap.data.api.response.MapBoxPlacesResponse
import com.eugene.tapbymap.extensions.Empty
import com.eugene.tapbymap.model.Place
import com.mapbox.api.geocoding.v5.models.CarmenFeature

/**
 * Created by Eugene on 27.08.2020
 */
class PlaceMapper {

    /**
     * To Transform from MapBoxPlacesResponse to Place
     * @see MapBoxPlacesResponse
     * @see Place
     * */
    fun transform(mapBoxPlacesResponse: MapBoxPlacesResponse)
            : Place? = mapBoxPlacesResponse.let { response ->
        return mutableListOf<Place>().apply {
            response.features.forEach { feature ->
                feature
                    .takeIf {
                        it.id.contains(PLACE_TYPE_ADDRESS) or
                        it.id.contains(POI_TYPE_ADDRESS)
                    }
                    ?.run {
                        add(transform(feature))
                    }
            }
        }.firstOrNull()
    }

    /**
     * To Transform from FeatureEntity to Place
     * @see FeatureEntity
     * @see Place
     * */
    fun transform(featureEntity: FeatureEntity): Place = featureEntity.run {
        Place(
            text,
            placeName,
            properties.address,
            this.center[0] /* latitude */,
            this.center[1] /* longitude */
        )
    }

    /**
     * To Transform from CarmenFeature to Place
     * @see CarmenFeature
     * @see Place
     * */
    fun transform(carmenFeature: CarmenFeature): Place = carmenFeature.run {
        Place(
            text() ?: String.Empty,
            placeName() ?: String.Empty,
            this.properties()?.get(PLACE_TYPE_ADDRESS)?.toString(),
            center()?.longitude() ?: 0.0,
            center()?.latitude() ?: 0.0
        )
    }

    companion object {
        private const val PLACE_TYPE_ADDRESS = "address"
        private const val POI_TYPE_ADDRESS = "poi"
    }
}