package com.yukihiro.droidkaigi2023.infra.preferences

import android.content.SharedPreferences
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AccessTokenCachePreferences @Inject constructor(
    private val preferences: SharedPreferences,
    private val json: Json
) {
    fun save(token: AccessToken) {
        preferences.edit().putString(PREFERENCE_ACCESS_TOKEN_KEY, json.encodeToString(token)).apply()
    }

    fun get(): AccessToken? {
        val cache = preferences.getString(PREFERENCE_ACCESS_TOKEN_KEY, null) ?: return null
        return json.decodeFromString(cache)
    }

    fun clear() {
        preferences.edit().remove(PREFERENCE_ACCESS_TOKEN_KEY).apply()
    }

    companion object {
        private const val PREFERENCE_ACCESS_TOKEN_KEY = "PREFERENCE_ACCESS_TOKEN_KEY"
    }
}