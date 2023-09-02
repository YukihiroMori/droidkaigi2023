package com.yukihiro.droidkaigi2023.infra.api.item.response

import kotlinx.serialization.Serializable

@Serializable
data class ItemListResponse(
    val data: Data?,
    val errorCode: Int?,
){
    @Serializable
    data class Data(
        val itemList: List<Item>
    )

    @Serializable
    data class Item(
        val id: String,
        val name: String,
        val image: String,
    )

    object ErrorCode{
        val NotAuthorized = 301
        val TokenExpired = 302
    }
}
