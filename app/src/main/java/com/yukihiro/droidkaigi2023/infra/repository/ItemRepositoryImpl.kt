package com.yukihiro.droidkaigi2023.infra.repository

import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.domain.exception.ItemException
import com.yukihiro.droidkaigi2023.domain.mapper.AccountMapper
import com.yukihiro.droidkaigi2023.domain.mapper.ItemMapper
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.Item
import com.yukihiro.droidkaigi2023.domain.repository.ItemRepository
import com.yukihiro.droidkaigi2023.infra.api.item.ItemListApi
import com.yukihiro.droidkaigi2023.infra.api.item.request.mapper.ItemListRequestMapper
import com.yukihiro.droidkaigi2023.infra.api.item.response.ItemListResponse
import com.yukihiro.droidkaigi2023.infra.api.login.response.LoginResponse
import com.yukihiro.droidkaigi2023.infra.database.item.dao.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemListApi: ItemListApi,
    private val itemDao: ItemDao
) : ItemRepository {

    override fun itemList(account: Account): Flow<List<Item>> =
        itemDao.itemListByAccountId(accountId = account.id.value)
            .map { itemList ->
                itemList.map { item -> ItemMapper.toModel(item) }
            }

    override suspend fun fetchItemList(
        token: AccessToken,
        account: Account
    ): List<Item> =
        withContext(Dispatchers.IO) {
            val request = ItemListRequestMapper.map(token)
            val response = itemListApi.fetchItemList(request)

            if (response.data != null) {
                val itemList = response.data.itemList.map { ItemMapper.toModel(it) }
                val itemEntityList = itemList.map { ItemMapper.toEntity(account, it) }
                itemDao.insertAll(*itemEntityList.toTypedArray())
                itemList
            } else {
                when (response.errorCode) {
                    ItemListResponse.ErrorCode.NotAuthorized -> throw ItemException.NotAuthorized
                    ItemListResponse.ErrorCode.TokenExpired -> throw ItemException.TokenExpired
                    else -> throw ItemException.ServerError
                }
            }
        }
}