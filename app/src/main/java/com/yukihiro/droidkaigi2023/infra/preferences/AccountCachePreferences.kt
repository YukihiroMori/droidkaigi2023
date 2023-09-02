package com.yukihiro.droidkaigi2023.infra.preferences

import android.content.SharedPreferences
import com.yukihiro.droidkaigi2023.domain.model.Account
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AccountCachePreferences @Inject constructor(
    private val preferences: SharedPreferences,
    private val json: Json
) {
    fun save(account: Account) {
        preferences.edit().putString(PREFERENCE_ACCOUNT_KEY, json.encodeToString(account)).apply()
    }

    fun get(): Account? {
        val cache = preferences.getString(PREFERENCE_ACCOUNT_KEY, null) ?: return null
        return json.decodeFromString(cache)
    }

    companion object {
        private const val PREFERENCE_ACCOUNT_KEY = "PREFERENCE_ACCOUNT_KEY"
    }
}