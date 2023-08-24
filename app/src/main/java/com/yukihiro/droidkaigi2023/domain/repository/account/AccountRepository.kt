package com.yukihiro.droidkaigi2023.domain.repository.account

import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.AccountId
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun currentAccount(): Flow<Account?>

    suspend fun login(
        email: Secret<String>,
        password: Secret<String>
    ): Account
}