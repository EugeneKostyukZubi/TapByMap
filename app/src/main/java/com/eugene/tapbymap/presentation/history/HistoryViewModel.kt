package com.eugene.tapbymap.presentation.history

import com.eugene.tapbymap.base.BaseViewModel
import com.eugene.tapbymap.base.Event
import com.eugene.tapbymap.extensions.event
import com.eugene.tapbymap.extensions.mutable
import com.eugene.tapbymap.model.Place
import com.eugene.tapbymap.repository.PlaceRepository
import kotlinx.coroutines.launch
import org.koin.core.inject

/**
 * Created by Eugene on 28.08.2020
 */
class HistoryViewModel : BaseViewModel() {

    private val placeRepository: PlaceRepository by inject()

    /* List of places for recycler view */
    val places = mutable<List<Place>>(emptyList())
    /* Last selected place in recycler view */
    val selectedPlace = mutable<Place>()

    /* Notify when place selected in recycler view by user */
    val onPlaceSelectedEvent = event<Place>()

    init {
        selectedPlace.observeForever {
            onPlaceSelectedEvent.postValue(Event(it))
        }
    }

    fun getSearchHistory() = launch {
        try {
            placeRepository.getSearchHistory().run {
                places.postValue(
                    /* Last added to the top of the list */
                    this.reversed()
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            onErrorEvent.postValue(Event(ex))
        }
    }

    fun removeSearchHistory() = launch {
        try {
            placeRepository.removeSearchHistory().run {
                places.postValue(emptyList())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            onErrorEvent.postValue(Event(ex))
        }
    }
}