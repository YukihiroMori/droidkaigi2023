package com.yukihiro.droidkaigi2023.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.yukihiro.droidkaigi2023.infra.preferences.AccessTokenCachePreferences
import com.yukihiro.droidkaigi2023.infra.preferences.AccountCachePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideAccountCachePreferences(
        preferences: SharedPreferences,
        json: Json,
    ): AccountCachePreferences {
        return AccountCachePreferences(
            preferences = preferences,
            json = json,
        )
    }

    @Provides
    @Singleton
    fun provideAccessTokenCachePreferences(
        preferences: SharedPreferences,
        json: Json,
    ): AccessTokenCachePreferences {
        return AccessTokenCachePreferences(
            preferences = preferences,
            json = json,
        )
    }
}