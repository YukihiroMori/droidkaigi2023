package com.yukihiro.droidkaigi2023.domain.repository

import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.domain.exception.ItemException
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface ItemRepository {
    fun itemList(account: Account): Flow<List<Item>>

    @Throws(
        ItemException.NotAuthorized::class,
        ItemException.TokenExpired::class,
        ItemException.ServerError::class
    )
    suspend fun fetchItemList(
        token: AccessToken,
        account: Account
    ): List<Item>
}