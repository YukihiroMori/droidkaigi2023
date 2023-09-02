package com.yukihiro.droidkaigi2023.infra.database.item.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yukihiro.droidkaigi2023.infra.database.item.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemEntity WHERE accountId = :accountId")
    fun itemListByAccountId(accountId: String): Flow<List<ItemEntity>>

    @Insert
    fun insertAll(vararg itemList: ItemEntity)

    @Query("DELETE FROM ItemEntity WHERE accountId = :accountId")
    fun deleteAllByAccountId(accountId: String)
}