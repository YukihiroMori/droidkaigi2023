package com.yukihiro.droidkaigi2023.domain.repository

import com.yukihiro.droidkaigi2023.domain.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import kotlin.jvm.Throws

interface AccountRepository {

    @Throws(AccountException.NotLoggedIn::class)
    fun getCurrentAccount(): Account

    @Throws(
        AccountException.WrongPassword::class,
        AccountException.NotRegistered::class,
        AccountException.ServerError::class
    )
    suspend fun login(
        email: Secret<String>,
        password: Secret<String>
    ): AccessToken
}