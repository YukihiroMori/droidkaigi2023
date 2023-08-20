package com.yukihiro.droidkaigi2023.infra.repository.exception

import com.yukihiro.droidkaigi2023.infra.exception.DomainException

sealed class AccountException: DomainException() {
    object NotLoggedIn: AccountException()

    object NotRegistered: AccountException()

    object WrongPassword: AccountException()

    object ServerError: AccountException()

    class Unexpected(val origin: Throwable) : AccountException()
}