package com.yukihiro.droidkaigi2023.infra.usecase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yukihiro.droidkaigi2023.domain.repository.AccessTokenRepository
import com.yukihiro.droidkaigi2023.domain.repository.AccountRepository
import com.yukihiro.droidkaigi2023.domain.repository.ItemRepository
import com.yukihiro.droidkaigi2023.domain.usecase.itemlist.FetchItemListUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.itemlist.FetchItemListUseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchItemListUseCaseImpl@Inject constructor(
    private val accountRepository: AccountRepository,
    private val accessTokenRepository: AccessTokenRepository,
    private val itemRepository: ItemRepository,
    private val crashlytics: FirebaseCrashlytics
): FetchItemListUseCase {

    override suspend fun invoke(): FetchItemListUseCaseResult =
            runCatching {
                val account = accountRepository.getCurrentAccount()
                val token = accessTokenRepository.getToken()
                itemRepository.clearItem(account)
                itemRepository.fetchItemList(
                    token = token,
                    account = account
                )
            }.fold(
                onSuccess = { FetchItemListUseCaseResult.Success },
                onFailure = {
                    crashlytics.recordException(it)
                    FetchItemListUseCaseResult.Failure(it)
                }
            )
}