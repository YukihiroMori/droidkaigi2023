package com.yukihiro.droidkaigi2023.infra.repository

import android.database.sqlite.SQLiteException
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.domain.exception.ItemException
import com.yukihiro.droidkaigi2023.domain.mapper.ItemMapper
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.Item
import com.yukihiro.droidkaigi2023.domain.repository.ItemRepository
import com.yukihiro.droidkaigi2023.infra.api.item.ItemListApi
import com.yukihiro.droidkaigi2023.infra.api.item.request.mapper.ItemListRequestMapper
import com.yukihiro.droidkaigi2023.infra.api.item.response.ItemListResponse
import com.yukihiro.droidkaigi2023.infra.database.item.dao.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.IOException
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemListApi: ItemListApi,
    private val itemDao: ItemDao
) : ItemRepository {

    override fun itemList(account: Account): Flow<List<Item>> =
        itemDao.itemListByAccountId(accountId = account.id.value)
            .map { itemList ->
                itemList.map { item -> ItemMapper.toModel(item) }
            }.catch {
                Firebase.crashlytics.log("Load Fail Account: $account")
                throw when (it) {
                    is SQLiteException -> ItemException.DatabaseAccessException(it)
                    else -> ItemException.UnexpectedException(it)
                }
            }

    override suspend fun fetchItemList(
        token: AccessToken,
        account: Account
    ): List<Item> =
        withContext(Dispatchers.IO) {
            runCatching {
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
            }.fold(
                onSuccess = { it },
                onFailure = {
                    throw when (it) {
                        is IOException -> ItemException.InternetException(it)
                        is SerializationException -> ItemException.IllegalResponseException(it)
                        else -> ItemException.UnexpectedException(it)
                    }
                }
            )
        }

    override suspend fun clearItem(account: Account) =
        withContext(Dispatchers.IO) {
            runCatching {
                itemDao.deleteAllByAccountId(accountId = account.id.value)
            }.fold(
                onSuccess = { it },
                onFailure = { throw ItemException.UnexpectedException(it) }
            )
        }
}