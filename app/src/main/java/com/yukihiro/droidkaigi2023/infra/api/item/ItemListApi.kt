package com.yukihiro.droidkaigi2023.infra.api.item

import com.yukihiro.droidkaigi2023.infra.api.item.request.ItemListRequest
import com.yukihiro.droidkaigi2023.infra.api.item.response.ItemListResponse
import com.yukihiro.droidkaigi2023.infra.api.login.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ItemListApi {

    @POST("item/list")
    suspend fun fetchItemList(
        @Body request: ItemListRequest
    ): ItemListResponse
}