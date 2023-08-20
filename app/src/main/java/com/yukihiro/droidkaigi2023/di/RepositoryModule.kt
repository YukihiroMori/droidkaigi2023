package com.yukihiro.droidkaigi2023.di

import com.yukihiro.droidkaigi2023.domain.repository.account.AccountRepository
import com.yukihiro.droidkaigi2023.infra.repository.AccountRepositoryImpl
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
}