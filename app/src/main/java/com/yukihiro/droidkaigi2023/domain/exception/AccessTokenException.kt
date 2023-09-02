package com.yukihiro.droidkaigi2023.domain.exception

sealed class AccessTokenException : DomainException() {
    object NotFoundToken: AccessTokenException()
}
