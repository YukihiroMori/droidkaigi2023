package com.yukihiro.droidkaigi2023.infra.api.login

import com.yukihiro.droidkaigi2023.infra.api.item.request.ItemListRequest
import com.yukihiro.droidkaigi2023.infra.api.login.request.LoginRequest
import com.yukihiro.droidkaigi2023.infra.api.login.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}