package com.example.vetrazcenter.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


private val Context.dataStore by preferencesDataStore("on_boarding_prefs")

@Module
@InstallIn(SingletonComponent::class)
object DataStorePrefs {

    @Provides
    fun provideOnBoardPreference(@ApplicationContext context: Context) = context.dataStore

}