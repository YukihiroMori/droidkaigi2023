package com.yukihiro.droidkaigi2023.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.yukihiro.droidkaigi2023.infra.datastore.AccountCacheDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class CacheDataStoreQualifier

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {


    @CacheDataStoreQualifier
    @Provides
    @Singleton
    fun provideCacheDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        corruptionHandler = null,
        migrations = emptyList(),
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = { File(context.cacheDir.resolve(DATA_STORE_CACHE_PREFERENCE_FILE_NAME).path) },
    )

    @Provides
    @Singleton
    fun provideAccountCacheDataStore(
        @CacheDataStoreQualifier
        cacheDataStore: DataStore<Preferences>,
        json: Json,
    ): AccountCacheDataStore {
        return AccountCacheDataStore(
            dataStore = cacheDataStore,
            json = json,
        )
    }

    private const val DATA_STORE_PREFERENCE_FILE_NAME = "droidkaigi2023.preferences_pb"
    private const val DATA_STORE_CACHE_PREFERENCE_FILE_NAME =
        "droidkaigi2023.cache.preferences_pb"
}