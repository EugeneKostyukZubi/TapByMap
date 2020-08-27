package com.eugene.tapbymap.data.preferences

import android.content.Context
import com.eugene.tapbymap.extensions.Empty
import com.eugene.tapbymap.model.Place
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Eugene on 28.08.2020
 */
class PreferencesHelper(private val context: Context) {
    private val pref =
        context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

    var searchHistory : List<Place>
        get() {
            pref.getString(PLACES_HISTORY_KEY, String.Empty)?.run {
                return Gson().fromJson(
                    this,
                    object : TypeToken<List<Place>>() {}.type
                ) ?: emptyList()
            } ?: return emptyList()
        }
        private set(value) {
            pref.edit().putString(PLACES_HISTORY_KEY, Gson().toJson(value)).apply()
        }

    fun addPlaceToHistory(place: Place) {
        searchHistory = searchHistory.plus(place)
    }

    companion object {
        private const val PREFERENCES_NAME = "TapByMapPreferences"
        private const val PLACES_HISTORY_KEY = "places_history_key"
    }
}