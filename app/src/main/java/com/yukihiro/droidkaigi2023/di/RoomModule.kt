package com.yukihiro.droidkaigi2023.di

import android.content.Context
import androidx.room.Room
import com.yukihiro.droidkaigi2023.infra.database.DroidKaigi2023Database
import com.yukihiro.droidkaigi2023.infra.database.item.dao.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun droidKaigi2023Database(
        @ApplicationContext context: Context
    ): DroidKaigi2023Database {
        return Room.databaseBuilder(context, DroidKaigi2023Database::class.java, "droidkaigi2023")
            .build()
    }

    @Provides
    @Singleton
    fun itemDao(droidKaigi2023Database: DroidKaigi2023Database): ItemDao {
        return droidKaigi2023Database.itemDao()
    }
}