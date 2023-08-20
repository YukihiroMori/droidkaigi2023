package com.yukihiro.droidkaigi2023.infra.api

import com.yukihiro.droidkaigi2023.infra.api.request.LoginRequest
import com.yukihiro.droidkaigi2023.infra.api.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}