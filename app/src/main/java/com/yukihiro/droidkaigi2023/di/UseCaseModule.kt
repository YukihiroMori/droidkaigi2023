package com.yukihiro.droidkaigi2023.di

import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCase
import com.yukihiro.droidkaigi2023.infra.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun loginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase
}