package com.yukihiro.droidkaigi2023.domain.repository.account

import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.AccountId
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun currentAccount(): Flow<Account?>

    suspend fun findByEmail(email: Secret<String>): Account?

    suspend fun login(
        email: Secret<String>,
        password: Secret<String>
    )

    suspend fun register(account: Account)

    suspend fun update(account: Account)

    suspend fun delete(accountId: AccountId)
}