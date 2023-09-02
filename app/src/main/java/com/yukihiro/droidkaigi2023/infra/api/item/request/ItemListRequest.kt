package com.yukihiro.droidkaigi2023.infra.api.item.request

import kotlinx.serialization.Serializable

@Serializable
data class ItemListRequest(
    val accessToken: String,
)
