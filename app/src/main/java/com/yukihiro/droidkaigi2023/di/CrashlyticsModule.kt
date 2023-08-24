package com.yukihiro.droidkaigi2023.di

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object CrashlyticsModule {

    @Provides
    @Singleton
    fun provideCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics
}