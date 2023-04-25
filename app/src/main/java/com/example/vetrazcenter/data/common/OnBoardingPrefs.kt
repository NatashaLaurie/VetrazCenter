package com.example.vetrazcenter.data.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class OnBoardingPrefs @Inject constructor(private val preferences: DataStore<Preferences>) {

    suspend fun saveOnBoarding(save: Boolean) {
        preferences.edit { onboardPreference ->
            onboardPreference[ONBOARD_KEY] = save
        }
    }

    fun fetchOnBoarding() = preferences.data.map { onboardPreference ->
        onboardPreference[ONBOARD_KEY] ?: false
    }

    companion object {
        val ONBOARD_KEY = booleanPreferencesKey("onBoard")
    }

}