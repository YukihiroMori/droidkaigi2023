package com.yukihiro.droidkaigi2023.domain.repository.account.exception

import com.yukihiro.droidkaigi2023.domain.exception.DomainException

sealed class AccountException: DomainException() {
    object NotLoggedIn: AccountException()

    object NotRegistered: AccountException()

    object WrongPassword: AccountException()

    object ServerError: AccountException()
}