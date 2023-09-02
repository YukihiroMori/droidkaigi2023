package com.yukihiro.droidkaigi2023.infra.api.item.request.mapper

import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import com.yukihiro.droidkaigi2023.infra.api.item.request.ItemListRequest

object ItemListRequestMapper {
    fun map(
        accessToken: AccessToken
    ) : ItemListRequest = ItemListRequest(
        accessToken = accessToken.value.data,
    )
}