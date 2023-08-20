package com.yukihiro.droidkaigi2023.infra.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yukihiro.droidkaigi2023.domain.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AccountCacheDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val json: Json
) {
    suspend fun setCurrentAccount(account: Account) {
        dataStore.edit { preferences ->
            preferences[DATA_STORE_ACCOUNT_KEY] = json.encodeToString(account)
        }
    }

    fun getCurrentAccountStream(): Flow<Account?> {
        return dataStore.data.map { preferences ->
            val cache = preferences[DATA_STORE_ACCOUNT_KEY]
            cache?.let { json.decodeFromString(it) }
        }
    }

    companion object {
        private val DATA_STORE_ACCOUNT_KEY =
            stringPreferencesKey("DATA_STORE_ACCOUNT_KEY")
    }
}