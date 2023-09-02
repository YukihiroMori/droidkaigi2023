package com.yukihiro.droidkaigi2023.infra.database.item.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["accountId","itemId"])
data class ItemEntity(
    val accountId: String,
    val itemId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String
)
