package com.yukihiro.droidkaigi2023.di

import com.yukihiro.droidkaigi2023.domain.repository.AccessTokenRepository
import com.yukihiro.droidkaigi2023.domain.repository.AccountRepository
import com.yukihiro.droidkaigi2023.domain.repository.ItemRepository
import com.yukihiro.droidkaigi2023.infra.repository.AccessTokenRepositoryImpl
import com.yukihiro.droidkaigi2023.infra.repository.AccountRepositoryImpl
import com.yukihiro.droidkaigi2023.infra.repository.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun accountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    fun accessTokenRepository(accessTokenRepositoryImpl: AccessTokenRepositoryImpl): AccessTokenRepository

    @Binds
    @Singleton
    fun itemRepository(itemRepositoryImpl: ItemRepositoryImpl): ItemRepository
}