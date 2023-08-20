package com.yukihiro.droidkaigi2023.infra.repository

import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.architecture.toSecret
import com.yukihiro.droidkaigi2023.domain.mapper.AccountMapper
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.AccountId
import com.yukihiro.droidkaigi2023.domain.model.User
import com.yukihiro.droidkaigi2023.domain.repository.account.AccountRepository
import com.yukihiro.droidkaigi2023.infra.api.LoginApi
import com.yukihiro.droidkaigi2023.infra.api.request.mapper.LoginRequestMapper
import com.yukihiro.droidkaigi2023.infra.api.response.LoginResponse
import com.yukihiro.droidkaigi2023.infra.datastore.AccountCacheDataStore
import com.yukihiro.droidkaigi2023.infra.repository.exception.AccountException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.withContext
import javax.inject.Inject

val ACCOUNT_LIST = mutableListOf(
    Account(
        user = User(name = "Joel Sessions"),
        email = "joel@email.com".toSecret(),
        password = "AAAAA".toSecret()
    ),
    Account(
        user = User(name = "Odell Sherwood Aiken"),
        email = "odell@email.com".toSecret(),
        password = "BBBBB".toSecret()
    ),
    Account(
        user = User(name = "Tavon Shawcross"),
        email = "tavon@email.com".toSecret(),
        password = "CCCCC".toSecret()
    ),
    Account(
        user = User(name = "Intyre Riddle"),
        email = "intyre@email.com".toSecret(),
        password = "DDDDD".toSecret()
    ),
    Account(
        user = User(name = "Hastings McOnie"),
        email = "hasting@email.com".toSecret(),
        password = "EEEEE".toSecret()
    ),
    Account(
        user = User(name = "Christian Winters"),
        email = "christian@email.com".toSecret(),
        password = "FFFFF".toSecret()
    ),
    Account(
        user = User(name = "Enoch Gib Morrissey"),
        email = "enoch@email.com".toSecret(),
        password = "GGGGG".toSecret()
    )
)

class AccountRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val accountStore: AccountCacheDataStore
) : AccountRepository {
    private val accountList: MutableStateFlow<MutableList<Account>> = MutableStateFlow(ACCOUNT_LIST)

    override fun currentAccount(): Flow<Account> =
        accountStore.getCurrentAccountStream()
            .catch { throw AccountException.Unexpected(it) }
            .map { it ?: throw AccountException.NotLoggedIn }

    override suspend fun login(
        email: Secret<String>,
        password: Secret<String>
    ) {
        withContext(Dispatchers.IO) {
            runCatching {
                val request = LoginRequestMapper.map(email, password)
                val response = loginApi.login(request)

                if (response.data != null) {
                    val account = AccountMapper.toModel(response.data.account)
                    accountStore.setCurrentAccount(account)
                } else {
                    when (response.errorCode) {
                        LoginResponse.ErrorCode.NotRegistered -> throw AccountException.NotRegistered
                        LoginResponse.ErrorCode.WrongPassword -> throw AccountException.WrongPassword
                        else -> throw AccountException.ServerError
                    }
                }
            }.onFailure { throw AccountException.Unexpected(it) }
        }
    }

    override suspend fun register(account: Account) {
        accountList.updateAndGet { it.apply { add(account) } }
    }

    override suspend fun findByEmail(email: Secret<String>): Account? {
        return accountList.value.find { it.email == email }
    }

    override suspend fun update(account: Account) {
        accountList.updateAndGet { list ->
            list.apply {
                replaceAll {
                    if (it.id == account.id) account
                    else it
                }
            }
        }
    }

    override suspend fun delete(accountId: AccountId) {
        accountList.updateAndGet { list ->
            list.apply {
                list.removeIf { it.id == accountId }
            }
        }
    }
}