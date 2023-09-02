package com.yukihiro.droidkaigi2023.domain.mapper

import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.Item
import com.yukihiro.droidkaigi2023.domain.model.ItemId
import com.yukihiro.droidkaigi2023.infra.api.item.response.ItemListResponse
import com.yukihiro.droidkaigi2023.infra.database.item.entity.ItemEntity

object ItemMapper {

    fun toModel(entity: ItemEntity): Item = Item(
        id = ItemId(entity.itemId),
        name = entity.name,
        image = entity.image
    )

    fun toEntity(account: Account, item: Item): ItemEntity = ItemEntity(
        accountId = account.id.value,
        itemId = item.id.value,
        name = item.name,
        image = item.image
    )

    fun toModel(response: ItemListResponse.Item): Item = Item(
        id = ItemId(response.id),
        name = response.name,
        image = response.image
    )

}