package com.yukihiro.droidkaigi2023.infra.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yukihiro.droidkaigi2023.infra.database.item.dao.ItemDao
import com.yukihiro.droidkaigi2023.infra.database.item.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class DroidKaigi2023Database : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}