package com.eugene.tapbymap.presentation.map

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eugene.tapbymap.R
import com.eugene.tapbymap.base.BaseToolbarFragment
import com.eugene.tapbymap.exception.NotFoundException
import com.eugene.tapbymap.extensions.observeEvent
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Eugene on 27.08.2020
 */
class MapFragment : BaseToolbarFragment() {

    override val screenTitleRes = R.string.map

    private var mapBox: MapboxMap? = null

    private val viewModel: MapViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Map box access token is configured here. This needs
        // to be called either in your application
        // object or in the same activity which contains the map view.
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))

        // This contains the MapView in XML and needs
        // to be called after the access token is configured.
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun initUi() {
        initMap()
        placeTextTextView.text = getString(R.string.default_text)
        searchActionButton.setOnClickListener {
            mapBox?.cameraPosition?.target?.run {
                viewModel.getPlaces(
                    requireContext(),
                    longitude,
                    latitude,
                    getString(R.string.mapbox_access_token)
                )
            }
        }
    }

    override fun bindUi() {
        viewModel.onPlaceFoundEvent.observeEvent(viewLifecycleOwner) {
            placeTextTextView.text = it.title
            placeAddressTextView.text = it.address
            placeNameTextView.text = it.name
        }

        viewModel.onErrorEvent.observeEvent(viewLifecycleOwner) {
            when (it) {
                is NotFoundException -> showToast(getString(R.string.not_found))
                else -> showToast(it.message ?: getString(R.string.something_went_wrong))
            }
        }
    }

    private fun initMap() {
        mapView.getMapAsync { mapBoxMap ->
            this.mapBox = mapBoxMap
            mapBoxMap.setStyle(Style.MAPBOX_STREETS) {
                runWithPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) {
                    enableLocationComponent(it)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(style: Style) {
        getActivatedLocationComponent(style)?.run {
            isLocationComponentEnabled = true
            cameraMode = CameraMode.TRACKING
            renderMode = RenderMode.NORMAL
            lastKnownLocation?.run { animateCamera(this) }
        }
    }

    private fun getActivatedLocationComponent(style: Style): LocationComponent? {
        return mapBox?.locationComponent?.apply {
            activateLocationComponent(
                LocationComponentActivationOptions.Builder(
                    requireContext(),
                    style
                ).build()
            )
        }
    }

    private fun animateCamera(location: Location) {
        val position = CameraPosition.Builder()
            .target(LatLng(location.latitude, location.longitude))
            .zoom(DEFAULT_CAMERA_ZOOM)
            .bearing(DEFAULT_CAMERA_BEARING)
            .build()

        mapBox?.animateCamera(
            CameraUpdateFactory.newCameraPosition(position),
            DEFAULT_CAMERA_ANIMATION_DURATION
        )
    }

    companion object {
        private const val DEFAULT_CAMERA_ZOOM = 14.0
        private const val DEFAULT_CAMERA_BEARING = 0.0
        private const val DEFAULT_CAMERA_ANIMATION_DURATION = 300
    }
}